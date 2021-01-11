package hha.spring.data.dataapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hha.spring.data.dataapi.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}
