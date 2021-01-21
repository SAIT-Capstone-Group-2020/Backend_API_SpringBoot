package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String> {
}
