package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.ui.CurrentHolidayBanner;
import hha.spring.data.dataapi.domain.ui.CurrentHomeBanner;
import hha.spring.data.dataapi.domain.ui.CurrentPromotion;
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
}
