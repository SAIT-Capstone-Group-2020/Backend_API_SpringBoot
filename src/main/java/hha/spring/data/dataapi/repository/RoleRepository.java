package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for JPA Repository - user_roles table
 * Default JPA implementation(framework) is hibernate
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Find by role name role.
     *
     * @param name the name
     * @return the role
     */
    Role findByRoleName(String name);
}
