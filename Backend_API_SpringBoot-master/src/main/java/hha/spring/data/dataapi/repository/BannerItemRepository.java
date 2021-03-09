package hha.spring.data.dataapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hha.spring.data.dataapi.domain.BannerItem;

public interface BannerItemRepository extends JpaRepository<BannerItem, Integer>{


    @Query(nativeQuery = true, name = "allBannerInfoMapping")
    public List<BannerItem> getAllBannerInfo();
}
