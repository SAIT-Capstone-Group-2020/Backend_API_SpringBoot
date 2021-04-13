package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for JPA Repository - User table
 * Default JPA implementation(framework) is hibernate
 */
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String username);

    @Query(value= "SELECT * FROM user WHERE uuid = ?1", nativeQuery = true)
    Users findByUuid(String uuid);
}
