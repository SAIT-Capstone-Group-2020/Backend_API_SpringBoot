package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.ui.HolidayBanner;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Holiday banner repository.
 */
public interface HolidayBannerRepository extends CrudRepository<HolidayBanner, Integer> {
}
