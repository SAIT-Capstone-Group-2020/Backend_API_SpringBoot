package hha.spring.data.dataapi.mapper;

import hha.spring.data.dataapi.domain.ui.tf.Promotion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromotionMapper {
    Promotion currentWeeklyPromotion();
}
