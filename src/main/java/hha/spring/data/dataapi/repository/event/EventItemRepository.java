package hha.spring.data.dataapi.repository.event;

import hha.spring.data.dataapi.domain.event.Event;
import hha.spring.data.dataapi.domain.event.EventItemDto;
import hha.spring.data.dataapi.domain.order.OrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventItemRepository extends JpaRepository<EventItemDto, Integer> {

    @Query(nativeQuery = true, name = "eventItemDataMapping")
    List<EventItemDto> listAllItem(int id);

}
