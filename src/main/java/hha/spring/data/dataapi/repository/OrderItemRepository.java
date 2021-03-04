package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = "SELECT * FROM order_items WHERE orders_id = ?1",
            nativeQuery = true)
    List<OrderItem> findByOrdersId(int id);
}
