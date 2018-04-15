package project.common;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import project.entity.DestinationFlight;
import project.entity.SourceFlight;

public final class SourceFlightHelper {

    private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yy HH:mm");

    public static void mergeSourceFlight(SourceFlight from, SourceFlight to) {
        to.setActArrDateTimeLt(mergeColumn(from.getActArrDateTimeLt(), to.getActArrDateTimeLt()));
        to.setArrAptCodeIata(mergeColumn(from.getArrAptCodeIata(), to.getArrAptCodeIata()));
        to.setAircraftNameScheduled(mergeColumn(from.getAircraftNameScheduled(), to.getAircraftNameScheduled()));
        to.setArrAptNameEs(mergeColumn(from.getArrAptNameEs(), to.getArrAptNameEs()));
        to.setCarrierAirlineNameEn(mergeColumn(from.getCarrierAirlineNameEn(), to.getCarrierAirlineNameEn()));
        to.setCarrierIcaoCode(mergeColumn(from.getCarrierIcaoCode(), to.getCarrierIcaoCode()));
        to.setCarrierNumber(mergeColumn(from.getCarrierNumber(), to.getCarrierNumber()));
        to.setDepAptNameEs(mergeColumn(from.getDepAptNameEs(), to.getDepAptNameEs()));
        to.setDepAptCodeIata(mergeColumn(from.getDepAptCodeIata(), to.getDepAptCodeIata()));
        to.setEstArrDateTimeLt(mergeColumn(from.getEstArrDateTimeLt(), to.getEstArrDateTimeLt()));
        to.setEstDepDateTimeLt(mergeColumn(from.getEstDepDateTimeLt(), to.getEstDepDateTimeLt()));
        to.setFlightAirlineNameEn(mergeColumn(from.getFlightAirlineNameEn(), to.getFlightAirlineNameEn()));
        to.setFlightAirlineName(mergeColumn(from.getFlightAirlineName(), to.getFlightAirlineName()));
        to.setFlightIcaoCode(mergeColumn(from.getFlightIcaoCode(), to.getFlightIcaoCode()));
        to.setFlightNumber(mergeColumn(from.getFlightNumber(), to.getFlightNumber()));
        to.setFltLegSeqNo(mergeColumn(from.getFltLegSeqNo(), to.getFltLegSeqNo()));
        to.setSchdArrOnlyDateLt(mergeColumn(from.getSchdArrOnlyDateLt(), to.getSchdArrOnlyDateLt()));
        to.setSchdArrOnlyTimeLt(mergeColumn(from.getSchdArrOnlyTimeLt(), to.getSchdArrOnlyTimeLt()));
        to.setSourceData(mergeColumn(from.getSourceData(), to.getSourceData()));
        to.setStatusInfo(mergeColumn(from.getStatusInfo(), to.getStatusInfo()));
        to.setActDepDateTimeLt(mergeColumn(from.getActDepDateTimeLt(), to.getActDepDateTimeLt()));
        to.setSchdDepOnlyDateLt(mergeColumn(from.getSchdDepOnlyDateLt(), to.getSchdDepOnlyDateLt()));
        to.setSchdDepOnlyTimeLt(mergeColumn(from.getSchdDepOnlyTimeLt(), to.getSchdDepOnlyTimeLt()));

        to.setBaggageInfo(mergeColumnAsList(from.getBaggageInfo(), to.getBaggageInfo()));
        to.setCounter(mergeColumnAsList(from.getCounter(), to.getCounter()));
        to.setGateInfo(mergeColumnAsList(from.getGateInfo(), to.getGateInfo()));
        to.setLoungeInfo(mergeColumnAsList(from.getLoungeInfo(), to.getLoungeInfo()));
        to.setTerminalInfo(mergeColumnAsList(from.getTerminalInfo(), to.getTerminalInfo()));
        to.setArrTerminalInfo(mergeColumnAsList(from.getArrTerminalInfo(), to.getArrTerminalInfo()));
    }

    private static String mergeColumnAsList(String from, String to) {
        if (to.endsWith("...")) {
            return to;
        }
        Set<String> counterOrderedSet = new LinkedHashSet<>(Arrays.asList(to.split(",")));
        counterOrderedSet.add(from);
        String result = String.join(",", counterOrderedSet);
        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }
        return result;
    }

    private static String mergeColumn(String from, String to) {
        return (to == null || to.isEmpty()) ? from : to;
    }

    public static DestinationFlight sourceFlightToDestinationFlight(SourceFlight sourceFlight) {
        DestinationFlight destinationFlight = new DestinationFlight();
        destinationFlight.setAdep(sourceFlight.getDepAptCodeIata());
        destinationFlight.setAdes(sourceFlight.getArrAptCodeIata());
        destinationFlight.setFlightCode(sourceFlight.getFlightIcaoCode());
        destinationFlight.setFlightNumber(sourceFlight.getFlightNumber());
        destinationFlight.setCarrierCode(sourceFlight.getCarrierIcaoCode());
        destinationFlight.setStatusInfo(sourceFlight.getStatusInfo());

        try {
            destinationFlight.setSchdDepLt(SDF.parse(sourceFlight.getSchdDepOnlyDateLt() + " " + sourceFlight.getSchdDepOnlyTimeLt()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setSchdDepLt(new Date(0));
        }

        try {
            destinationFlight.setSchdArrLt(SDF.parse(sourceFlight.getSchdArrOnlyDateLt() + " " + sourceFlight.getSchdArrOnlyTimeLt()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setSchdArrLt(new Date(0));
        }

        try {
            destinationFlight.setEstDepLt(SDF.parse(sourceFlight.getEstDepDateTimeLt()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setEstDepLt(new Date(0));
        }

        try {
            destinationFlight.setEstArrLt(SDF.parse(sourceFlight.getEstDepDateTimeLt()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setEstArrLt(new Date(0));
        }

        try {
            destinationFlight.setActDepLt(SDF.parse(sourceFlight.getActDepDateTimeLt()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setActDepLt(new Date(0));
        }

        try {
            destinationFlight.setActArrLt(SDF.parse(sourceFlight.getActArrDateTimeLt()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setActArrLt(new Date(0));
        }

        try {
            destinationFlight.setFltLegSeqNo(Integer.parseInt(sourceFlight.getFltLegSeqNo()));
        } catch (Exception e) {
            System.err.println(e);
            destinationFlight.setFltLegSeqNo(0);
        }

        destinationFlight.setAircraftNameScheduled(sourceFlight.getAircraftNameScheduled());
        destinationFlight.setBaggageInfo(sourceFlight.getBaggageInfo());
        destinationFlight.setCounter(sourceFlight.getCounter());
        destinationFlight.setGateInfo(sourceFlight.getGateInfo());
        destinationFlight.setLoungeInfo(sourceFlight.getLoungeInfo());
        destinationFlight.setTerminalInfo(sourceFlight.getTerminalInfo());
        destinationFlight.setArrTerminalInfo(sourceFlight.getArrTerminalInfo());
        destinationFlight.setSourceData(sourceFlight.getSourceData());
        destinationFlight.setCreatedAt(new Date(sourceFlight.getCreatedAt().longValue() * 1000));

        return destinationFlight;
    }
}
