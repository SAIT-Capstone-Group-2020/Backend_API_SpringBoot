package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for JPA Repository - Product table
 * Default JPA implementation(framework) is hibernate
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Find by name product.
     *
     * @param name the name
     * @return the product
     */
    Product findByName(String name);

    /**
     * Find by id product.
     *
     * @param id the id
     * @return the product
     */
    Product findById(int id);

    /**
     * Find all page.
     *
     * @param name     the name
     * @param category the category
     * @param brand    the brand
     * @param pageable the pageable
     * @return the page
     */
    @Query(value = "SELECT product_id, product_name, description, brand_name, retail_price, active, image_url, c.category_id, quantity, weight_value, weight_type_id "
            +"FROM product AS p JOIN category AS c ON(p.category_id = c.category_id) WHERE " +
            "(p.product_name LIKE LOWER(CONCAT('%',?1,'%'))) " +
            "AND (c.category_name LIKE LOWER(CONCAT('%',?2,'%'))) "
            +"AND (p.brand_name LIKE LOWER(CONCAT('%',?3,'%')))"
            ,
            nativeQuery = true)
    Page<Product> findAll(String name, String category, String brand, Pageable pageable);

    /**
     * Find by name and brand product.
     *
     * @param name  the name
     * @param brand the brand
     * @return the product
     */
    @Query(value = "SELECT * FROM product WHERE product_name = ?1 AND brand_name = ?2",
            nativeQuery = true)
    Product findByNameAndBrand(String name, String brand);
}
