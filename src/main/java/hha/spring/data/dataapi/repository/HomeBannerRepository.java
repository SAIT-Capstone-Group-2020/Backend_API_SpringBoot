package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.domain.ui.data.CurrHoliday;
import hha.spring.data.dataapi.domain.ui.data.CurrHomeBanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Home banner repository.
 */
@Repository
public interface HomeBannerRepository extends CrudRepository<HomeBanner, Integer> {

    /**
     * Query current home banner list.
     *
     * @return the list
     */
    @Query(nativeQuery = true)
    List<CurrHomeBanner> queryCurrentHomeBanner();

    /**
     * Query current holiday banner curr holiday.
     *
     * @return the curr holiday
     */
    @Query(nativeQuery = true)
    CurrHoliday queryCurrentHolidayBanner();

}
