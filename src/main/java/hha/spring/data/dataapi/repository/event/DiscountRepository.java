package hha.spring.data.dataapi.repository.event;

import hha.spring.data.dataapi.domain.event.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for JPA Repository - Discount table
 * Default JPA implementation(framework) is hibernate
*/
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
