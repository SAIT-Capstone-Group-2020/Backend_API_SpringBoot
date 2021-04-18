package hha.spring.data.dataapi.repository.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import hha.spring.data.dataapi.domain.event.Event;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Interface for JPA Repository - event table
 * Default JPA implementation(framework) is hibernate
 */
public interface EventRepository extends JpaRepository<Event, Integer> {

    /**
     * Gets event.
     *
     * @param name         the name
     * @param minEventDate the min event date
     * @param maxEventDate the max event date
     * @param pageable     the pageable
     * @return the event
     */
    @Query(value= "SELECT * "
            +"FROM event " +
            "WHERE " +
            "(event_title LIKE CONCAT('%',?1,'%')) " +
            "AND (start_date >= ?2 OR end_date < ?3 + INTERVAL 1 DAY) "
            ,countQuery =
            "SELECT * "
                    +"FROM event " +
                    "WHERE " +
                    "(event_title LIKE CONCAT('%',?1,'%')) " +
                    "AND (start_date >= ?2 OR end_date < ?3 + INTERVAL 1 DAY) "
            , nativeQuery = true)
    Page<Event> getEvent(String name, Date minEventDate, Date maxEventDate, Pageable pageable);

}
