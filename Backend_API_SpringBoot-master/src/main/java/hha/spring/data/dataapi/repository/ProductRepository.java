package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    Product findById(int id);

    @Query(value = "SELECT product_id, product_name, description, brand_name, retail_price, active, image_url, c.category_id, quantity, weight_value, weight_type_id "
            +"FROM product AS p JOIN category AS c ON(p.category_id = c.category_id) WHERE " +
            "(p.product_name LIKE LOWER(CONCAT('%',?1,'%'))) " +
            "AND (c.category_name LIKE LOWER(CONCAT('%',?2,'%'))) "
            +"AND (p.brand_name LIKE LOWER(CONCAT('%',?3,'%')))"
            ,
            nativeQuery = true)
    Page<Product> findAll(String name, String category, String brand, Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE product_name = ?1 AND brand_name = ?2",
            nativeQuery = true)
    Product findByNameAndBrand(String name, String brand);
}
