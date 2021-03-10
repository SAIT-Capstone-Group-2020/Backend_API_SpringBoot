package hha.spring.data.dataapi.repository.event;

import hha.spring.data.dataapi.domain.event.Discount;
import hha.spring.data.dataapi.domain.event.EventItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
