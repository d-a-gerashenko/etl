package project.repository;

import project.entity.Flight;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    @Query(""
            + "SELECT "
            + "coalesce(MAX(f.sourceItemId), 0)"
            + "FROM "
            + "Flight f "
    )
    public Long findLastSourceItemId();
    
    public List<Flight> findByComplete(boolean Complete, Pageable pageable);
    
    @Query(""
            + "SELECT "
            + "COUNT(*)"
            + "FROM "
            + "Flight f "
            + "WHERE f.complete = true "
    )
    public Long countComplete();
    
    @Query(""
            + "SELECT "
            + "COUNT(*)"
            + "FROM "
            + "Flight f "
            + "WHERE f.complete = false "
    )
    public Long countUncomplete();
}
