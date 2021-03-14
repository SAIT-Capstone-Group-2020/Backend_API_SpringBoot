package hha.spring.data.dataapi.repository.order;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.order.OrderInfoDto;
import hha.spring.data.dataapi.domain.order.OrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderItemDto, Integer> {

    @Query(nativeQuery = true, name = "orderItemDataMapping")
    List<OrderItemDto> listAllItem(int id);
}
