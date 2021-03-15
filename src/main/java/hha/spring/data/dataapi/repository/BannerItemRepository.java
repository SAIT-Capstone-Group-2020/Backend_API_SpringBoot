package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.BannerItem;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface BannerItemRepository extends JpaRepository<BannerItem, Integer> {

=======
public interface BannerItemRepository extends JpaRepository<BannerItem, Integer>{

    /*
    @Query(nativeQuery = true, name = "allBannerInfoMapping")
    public List<BannerItem> getAllBannerInfo();*/
>>>>>>> 01bda963616f4e6a2ae5af778129d61b8a33418e
}
