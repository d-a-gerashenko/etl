package project.service;

import project.entity.AppValue;
import project.entity.SourceFlight;
import project.entity.Flight;
import project.repository.DestinationFlightRepository;
import project.repository.AppValueRepository;
import project.repository.FlightRepository;
import project.repository.SourceFlightRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import project.common.SourceFlightHelper;

@Service
public class EtlService {

    private final AppValueRepository appValueRepository;

    private final ThreadPoolTaskExecutor executor;
    private final SourceFlightRepository sourceFlightRepository;
    private final FlightRepository flightRepository;
    private final DestinationFlightRepository destinationFlightRepository;
    private final AtomicBoolean isActive = new AtomicBoolean(false);

    private volatile boolean stopFlag = false;

    public EtlService(
            ThreadPoolTaskExecutor executor,
            AppValueRepository appValueRepository,
            SourceFlightRepository sourceFlightRepository,
            FlightRepository flightRepository,
            DestinationFlightRepository destinationFlightRepository
    ) {
        this.executor = executor;
        this.appValueRepository = appValueRepository;
        this.sourceFlightRepository = sourceFlightRepository;
        this.flightRepository = flightRepository;
        this.destinationFlightRepository = destinationFlightRepository;
    }

    private Runnable getTask() {
        return () -> {
            try {
                int currentState = loadCurrentState();
                switch (currentState) {
                    case 0: {
                        step0();
                        if (stopFlag) {
                            return;
                        }
                    }
                    case 1: {
                        step1();
                        if (stopFlag) {
                            return;
                        }
                    }
                    case 2: {
                        step2();
                    }
                }
            } catch (Throwable e) {
                System.err.println(e);
            } finally {
                isActive.set(false);
            }
        };
    }

    private void step0() throws Exception {
        int pageNumber = 0;
        List<?> quniqueFlights;
        do {
            if (stopFlag) {
                return;
            }
            quniqueFlights = sourceFlightRepository.findAllQniqueFlightsFromId(
                    flightRepository.findLastSourceItemId(),
                    PageRequest.of(
                            pageNumber++,
                            100
                    )
            );
            quniqueFlights.forEach((t) -> {
                Map<String, ?> quniqueFlight = (Map<String, ?>) t;
                Flight flight = new Flight();
                flight.setFlightIcaoCode((String) quniqueFlight.get("flightIcaoCode"));
                flight.setFlightNumber((String) quniqueFlight.get("flightNumber"));
                flight.setSourceItemId((Long) quniqueFlight.get("id"));
                flightRepository.save(flight);
            });
        } while (!quniqueFlights.isEmpty());
        saveCurrentState(1);
    }

    private void step1() throws Exception {
        List<Long> flightIdLocks = Collections.synchronizedList(new ArrayList(executor.getCorePoolSize()));
        CountDownLatch tasksLatch = new CountDownLatch(executor.getCorePoolSize());
        for (int i = 0; i < executor.getCorePoolSize(); i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        Flight flight = null;
                        while (flight == null) {
                            if (stopFlag) {
                                return;
                            }
                            List<Flight> flightsToLock = flightRepository.findByComplete(false, PageRequest.of(
                                    0,
                                    executor.getCorePoolSize()
                            ));
                            if (flightsToLock.isEmpty()) {
                                return;
                            } else {
                                synchronized (flightIdLocks) {
                                    for (Flight flightToLock : flightsToLock) {
                                        if (!flightIdLocks.contains(flightToLock.getId())) {
                                            flightIdLocks.add(flightToLock.getId());
                                            flight = flightToLock;
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        while (true) {
                            if (stopFlag) {
                                return;
                            }
                            List<SourceFlight> sourceFlightsForFlight = sourceFlightRepository.findAllByFlight(
                                    flight.getFlightIcaoCode(),
                                    flight.getFlightNumber(),
                                    PageRequest.of(
                                            0,
                                            500
                                    )
                            );
                            if (sourceFlightsForFlight.size() < 2) {
                                flight.setComplete(true);
                                flightRepository.save(flight);
                                flightIdLocks.remove(flight.getId());
                                break;
                            }
                            SourceFlight sourceFlightToSave = sourceFlightsForFlight.remove(0);
                            sourceFlightsForFlight.forEach((sourceFlightToDelete) -> {
                                SourceFlightHelper.mergeSourceFlight(sourceFlightToDelete, sourceFlightToSave);
                            });
                            sourceFlightRepository.save(sourceFlightToSave);
                            sourceFlightRepository.deleteAll(sourceFlightsForFlight);
                        }
                    }
                } catch (Throwable e) {
                    System.err.println(e);
                    System.err.println("Aborting execution.");
                    stop();
                } finally {
                    tasksLatch.countDown();
                }
            });
        }
        tasksLatch.await();
        if (stopFlag) {
            return;
        }
        saveCurrentState(2);
    }

    private void step2() throws Exception {
        int pageSize = 100;
        long offset = destinationFlightRepository.count();
        int pageNumber = (int) (offset / pageSize);
        long itemNum = pageNumber * pageSize;
        List<SourceFlight> sourceFlights;
        do {
            if (stopFlag) {
                return;
            }
            sourceFlights = sourceFlightRepository.findAllByOrderById(
                    PageRequest.of(
                            pageNumber,
                            pageSize
                    )
            );
            for (SourceFlight sourceFlight : sourceFlights) {
                if (itemNum >= offset) {
                    destinationFlightRepository.save(SourceFlightHelper.sourceFlightToDestinationFlight(sourceFlight));
                }
                itemNum++;
            }
            pageNumber++;
        } while (!sourceFlights.isEmpty());
        saveCurrentState(3);
    }

    public void start() {
        if (!isActive.compareAndSet(false, true)) {
            return;
        }
        stopFlag = false;
        new Thread(getTask(), "EtlSevice started at " + System.currentTimeMillis()).start();
    }

    public void stop() {
        stopFlag = true;
    }

    public boolean isStoping() {
        return stopFlag == true && isExecuting();
    }

    public boolean isExecuting() {
        return isActive.get();
    }

    /**
     * @return 0 - Extract, 1 - Transform, 2 - Load, 3 - Complete
     */
    public int loadCurrentState() {
        AppValue stateValue = appValueRepository.findOneByKey("state");
        return (stateValue == null) ? 0 : Integer.valueOf(stateValue.getValue());
    }

    private void saveCurrentState(int state) {
        AppValue stateValue = appValueRepository.findOneByKey("state");
        if (stateValue == null) {
            stateValue = new AppValue("state", String.valueOf(state));
        } else {
            stateValue.setValue(String.valueOf(state));
        }
        appValueRepository.save(stateValue);
    }

    public float loadCompletePercent() {
        int currentState = loadCurrentState();
        switch (currentState) {
            case 0:
                return sourceFlightRepository.countBeforeId(flightRepository.findLastSourceItemId()) * 100f / sourceFlightRepository.count();
            case 1:
                return flightRepository.countComplete() * 100f / flightRepository.count();
            case 2:
                return destinationFlightRepository.count() * 100f / sourceFlightRepository.count();
        }
        return 100;
    }
}
