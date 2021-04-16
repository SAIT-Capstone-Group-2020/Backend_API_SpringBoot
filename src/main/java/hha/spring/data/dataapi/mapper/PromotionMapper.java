package hha.spring.data.dataapi.mapper;

import hha.spring.data.dataapi.domain.ui.data.CurrPromotion;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mybatis Mapper
 */
@Mapper
public interface PromotionMapper {
    /**
     * get current weekly promotion
     *
     * @return Current Promotion if have, null otherwise.
     */
    CurrPromotion currentWeeklyPromotion();
}
