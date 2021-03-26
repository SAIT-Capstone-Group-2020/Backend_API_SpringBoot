package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.ui.CurrentHolidayBanner;
import hha.spring.data.dataapi.domain.ui.CurrentHomeBanner;
import hha.spring.data.dataapi.domain.ui.CurrentPromotion;
import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.domain.ui.tf.TfCurrHoliday;
import hha.spring.data.dataapi.domain.ui.tf.TfCurrHomeBanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeBannerRepository extends CrudRepository<HomeBanner, Integer> {

    @Query(nativeQuery = true)
    List<CurrentHomeBanner> queryCurrentHomeBanner();

    @Query(nativeQuery = true)
    CurrentHolidayBanner queryCurrentHolidayBanner();

    @Query(nativeQuery = true)
    List<CurrentPromotion> queryCurrentPromotion();

    @Query(nativeQuery = true)
    List<TfCurrHomeBanner> queryTFCurrentHomeBanner();

    @Query(nativeQuery = true)
    TfCurrHoliday queryTFCurrentHolidayBanner();


}
