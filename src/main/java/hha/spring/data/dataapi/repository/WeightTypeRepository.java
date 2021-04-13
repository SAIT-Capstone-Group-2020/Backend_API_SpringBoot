package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.WeightType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for JPA Repository - Weight_type table
 * Default JPA implementation(framework) is hibernate
 */
public interface WeightTypeRepository extends JpaRepository<WeightType, Integer> {
}
