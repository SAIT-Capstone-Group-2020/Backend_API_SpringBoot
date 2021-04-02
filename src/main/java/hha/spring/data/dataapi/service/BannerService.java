package hha.spring.data.dataapi.service;


import hha.spring.data.dataapi.domain.ui.data.CurrPromotion;
import hha.spring.data.dataapi.domain.ui.data.CurrHoliday;
import hha.spring.data.dataapi.domain.ui.data.CurrHomeBanner;
import hha.spring.data.dataapi.mapper.PromotionMapper;
import hha.spring.data.dataapi.repository.HomeBannerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BannerService {
    private HomeBannerRepository homeBannerRepository;
    private PromotionMapper promotionMapper;
    public BannerService(HomeBannerRepository homeBannerRepository, PromotionMapper promotionMapper) {
        this.homeBannerRepository = homeBannerRepository;
        this.promotionMapper = promotionMapper;
    }




    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CurrHoliday findCurrentHolidayBanner() {
        return homeBannerRepository.queryCurrentHolidayBanner();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<CurrHomeBanner> findCurrentHomeBanner() {
        return homeBannerRepository.queryCurrentHomeBanner();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CurrPromotion findCurrentPromotion(){
        return promotionMapper.currentWeeklyPromotion();
    }


}

