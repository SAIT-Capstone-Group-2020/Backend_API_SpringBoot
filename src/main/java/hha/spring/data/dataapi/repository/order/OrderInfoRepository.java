package hha.spring.data.dataapi.repository.order;

import hha.spring.data.dataapi.domain.order.OrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for JPA Repository - Order item data object
 * Default JPA implementation(framework) is hibernate
 */
public interface OrderInfoRepository extends JpaRepository<OrderItemDto, Integer> {

    /**
     * List all item list.
     *
     * @param id the id
     * @return the list
     */
    @Query(nativeQuery = true, name = "orderItemDataMapping")
    List<OrderItemDto> listAllItem(int id);
}
