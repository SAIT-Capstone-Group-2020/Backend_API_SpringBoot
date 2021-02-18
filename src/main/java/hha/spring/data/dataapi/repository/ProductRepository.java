package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    Product findById(int id);

    @Query(value= "SELECT * FROM product WHERE product_name LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Product> findByKeyword(String keyword);
}
