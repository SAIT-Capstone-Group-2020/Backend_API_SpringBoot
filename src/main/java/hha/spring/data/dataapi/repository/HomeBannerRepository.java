package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.domain.ui.data.CurrHoliday;
import hha.spring.data.dataapi.domain.ui.data.CurrHomeBanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeBannerRepository extends CrudRepository<HomeBanner, Integer> {

    @Query(nativeQuery = true)
    List<CurrHomeBanner> queryCurrentHomeBanner();

    @Query(nativeQuery = true)
    CurrHoliday queryCurrentHolidayBanner();

}
