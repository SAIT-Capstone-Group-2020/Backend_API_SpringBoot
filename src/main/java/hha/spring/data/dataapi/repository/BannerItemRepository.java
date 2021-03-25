package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.BannerItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerItemRepository extends JpaRepository<BannerItem, Integer> {

}
