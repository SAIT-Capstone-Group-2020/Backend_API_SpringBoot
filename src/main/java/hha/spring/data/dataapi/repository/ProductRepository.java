package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
