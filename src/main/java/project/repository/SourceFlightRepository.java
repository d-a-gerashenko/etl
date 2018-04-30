package project.repository;

import project.entity.SourceFlight;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SourceFlightRepository extends JpaRepository<SourceFlight, Long> {

    @Query(""
            + "SELECT "
            + "new map(f.flightIcaoCode as flightIcaoCode, f.flightNumber as flightNumber, min(f.id) as id) "
            + "FROM "
            + "SourceFlight f "
            + "GROUP BY f.flightIcaoCode, f.flightNumber "
            + "HAVING min(f.id) > :lastId "
            + "ORDER BY id"
    )
    public List<?> findAllQniqueFlightsFromId(@Param("lastId") Long lastId, Pageable pageable);
    
    @Query(""
            + "SELECT "
            + "COUNT(*)"
            + "FROM "
            + "SourceFlight f "
            + "WHERE f.id <= :lastId "
    )
    public Long countBeforeId(@Param("lastId") Long lastId);

    @Query(""
            + "SELECT "
            + "f "
            + "FROM "
            + "SourceFlight f "
            + "WHERE f.flightIcaoCode = :flightIcaoCode AND f.flightNumber = :flightNumber "
            + "ORDER BY f.createdAt DESC"
    )
    public List<SourceFlight> findAllByFlight(@Param("flightIcaoCode") String flightIcaoCode, @Param("flightNumber") String flightNumber, Pageable pageable);

    public List<SourceFlight> findAllByOrderById(Pageable pageable);
}
