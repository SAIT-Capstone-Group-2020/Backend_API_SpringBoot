package hha.spring.data.dataapi.mapper;

import hha.spring.data.dataapi.domain.ui.data.CurrPromotion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromotionMapper {
    CurrPromotion currentWeeklyPromotion();
}
