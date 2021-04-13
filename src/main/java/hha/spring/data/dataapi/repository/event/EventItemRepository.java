package hha.spring.data.dataapi.repository.event;

import hha.spring.data.dataapi.domain.event.EventItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for JPA Repository - event item data object
 * Default JPA implementation(framework) is hibernate
 */
public interface EventItemRepository extends JpaRepository<EventItemDto, Integer> {

    @Query(nativeQuery = true, name = "eventItemDataMapping")
    List<EventItemDto> listAllItem(int id);

}
