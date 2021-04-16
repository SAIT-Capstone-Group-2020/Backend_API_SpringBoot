package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.ui.HomeBannerItem;
import hha.spring.data.dataapi.domain.ui.HomeBannerItemDto;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Home banner item dto repostiroy.
 */
public interface HomeBannerItemDtoRepostiroy extends CrudRepository<HomeBannerItemDto, Integer> {
}
