package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for JPA Repository - user_roles table
 * Default JPA implementation(framework) is hibernate
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
