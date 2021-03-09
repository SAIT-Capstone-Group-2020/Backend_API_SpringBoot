package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
