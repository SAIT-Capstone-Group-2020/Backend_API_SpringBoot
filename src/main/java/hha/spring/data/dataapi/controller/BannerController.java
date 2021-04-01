package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * controller for get all information for swipper ui control in front-end
 */
@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/api/v2/ui/allbanner")
    public HashMap<String, Object> getAllBanner() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("home", bannerService.findCurrentHomeBanner());
        map.put("holiday", bannerService.findCurrentHolidayBanner());
        map.put("promotion", bannerService.findCurrentPromotion());
        return map;
    }
}
