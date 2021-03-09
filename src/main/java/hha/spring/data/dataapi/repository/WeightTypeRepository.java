package hha.spring.data.dataapi.repository;


import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.domain.WeightType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightTypeRepository extends JpaRepository<WeightType, Integer> {
}
