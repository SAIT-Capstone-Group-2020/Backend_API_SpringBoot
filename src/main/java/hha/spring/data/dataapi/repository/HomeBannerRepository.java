package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.ui.HomeBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeBannerRepository extends CrudRepository<HomeBanner, Integer> {
}
