package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM Product WHERE name = ?1", nativeQuery = true)
    Item findBySearchKeyword(String keyword);

    //need to change it to => join using where start/end date of event => union with product not in discount(check date)

}
