package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM product WHERE CONCAT(name, code, category) LIKE %:val%", nativeQuery = true)
	Page<Product> findAll(@Param("val") String val, Pageable pageable);

}
