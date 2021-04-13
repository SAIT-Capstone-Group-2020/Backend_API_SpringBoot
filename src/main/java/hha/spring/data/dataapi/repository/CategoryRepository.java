package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for JPA Repository - Category table
 * Default JPA implementation(framework) is hibernate
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
