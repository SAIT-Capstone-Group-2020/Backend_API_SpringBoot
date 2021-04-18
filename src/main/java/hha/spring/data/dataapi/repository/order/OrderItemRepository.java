package hha.spring.data.dataapi.repository.order;

import hha.spring.data.dataapi.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for JPA Repository - order_items table
 * Default JPA implementation(framework) is hibernate
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    /**
     * Find by orders id list.
     *
     * @param id the id
     * @return the list
     */
    @Query(value = "SELECT * FROM order_items WHERE orders_id = ?1",
            nativeQuery = true)
    List<OrderItem> findByOrdersId(int id);
}
