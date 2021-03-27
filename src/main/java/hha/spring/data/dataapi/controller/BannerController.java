package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.ui.CurrentHolidayBanner;
import hha.spring.data.dataapi.domain.ui.CurrentHomeBanner;
import hha.spring.data.dataapi.domain.ui.CurrentPromotion;
import hha.spring.data.dataapi.repository.HomeBannerRepository;
import hha.spring.data.dataapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * controller for get all information for swipper ui control in front-end
 */
@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/api/v2/ui/allbanner")
    public HashMap<String, Object> getAllBanner() {
        final List<CurrentHomeBanner> currentHomeBanner = bannerService.findCurrentHomeBanner();
        final CurrentHolidayBanner currentHolidayBanner = bannerService.findCurrentHolidayBanner();
        final List<CurrentPromotion> currentPromotion = bannerService.findCurrentPromotion();
        final HashMap<String, Object> result = new HashMap<>();
        result.put("home", currentHomeBanner);
        result.put("holiday", currentHolidayBanner);
        result.put("promotion", currentPromotion);
        return result;
    }

    @GetMapping("/api/v2/ui/allbanner_tf")
    public HashMap<String, Object> getAllBannerTest() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("home", bannerService.findTFCurrentHomeBanner());
        map.put("holiday", bannerService.findTFCurrentHolidayBanner());
        map.put("promotion", bannerService.findCurrentPromotion());
        return map;
    }

    @GetMapping("/api/v2/ui/allbanner_tf2")
    public HashMap<String, Object> getAllBannerTest2() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("home", bannerService.findTFCurrentHomeBanner());
        map.put("holiday", bannerService.findTFCurrentHolidayBanner());
        map.put("promotion", bannerService.findCurrentPromotionTf2());
        return map;
    }
}
