package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product WHERE category = ?1", nativeQuery = true)
    List<Product> findAllByCategory(int cateId);

}
