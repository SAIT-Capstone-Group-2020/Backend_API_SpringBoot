package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
