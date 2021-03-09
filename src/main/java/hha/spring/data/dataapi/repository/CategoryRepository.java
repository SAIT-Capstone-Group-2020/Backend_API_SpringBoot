package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
