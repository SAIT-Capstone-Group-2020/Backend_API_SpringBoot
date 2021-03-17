package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Swipper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwipperRepository extends CrudRepository<Swipper, Integer> {
    public Swipper findFirstByTypeEqualsOrderByIdDesc(String type);
}
