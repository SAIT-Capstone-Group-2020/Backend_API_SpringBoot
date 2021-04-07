package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.ui.EventBanner;
import hha.spring.data.dataapi.domain.ui.HolidayBanner;
import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.domain.ui.HomeBannerItem;
import hha.spring.data.dataapi.domain.ui.request.EventBannerRequest;
import hha.spring.data.dataapi.domain.ui.request.HolidayBannerRequest;
import hha.spring.data.dataapi.domain.ui.request.HomeBannerItemRequest;
import hha.spring.data.dataapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String, Object> makeResponse(int code, String msg, Object data) {
        final HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    @PostMapping(path = "/api/v2/admin/home_banner")
    public Map<String, Object> newHomeBanner(@RequestBody HomeBanner homeBanner) {
        try {
            bannerService.createHomeBanner(homeBanner);
            return makeResponse(0, "ok", homeBanner);
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return makeResponse(1, "error", null);
    }

    @PutMapping(path = "/api/v2/admin/home_banner")
    public Map<String, Object> updateHomeBanner(@RequestBody HomeBanner homeBanner) {
        try {
            final HomeBanner banner = bannerService.updateHomeBanner(homeBanner);
            return makeResponse(0, "ok", banner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @DeleteMapping(path = "/api/v2/admin/home_banner/{id}")
    public Map<String, Object> deleteHomeBanner(@PathVariable(name = "id") Integer id) {
        try {
            final HomeBanner banner = bannerService.deleteHomeBanner(id);
            return makeResponse(0, "ok", banner);
        } catch (Exception e) {
            e.printStackTrace();
            return makeResponse(1, "error", null);
        }
    }

    @PostMapping(path = "/api/v2/admin/home_banner_item")
    public Map<String, Object> newHomeBannerItem(@RequestBody HomeBannerItemRequest request) {
        try {
            final HomeBannerItem item = bannerService.createHomeBannerItem(request);
            item.setHomeBanner(null);
            return makeResponse(0, "ok", item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @PutMapping(path = "/api/v2/admin/home_banner_item")
    public Map<String, Object> updateHomeBannerItem(@RequestBody HomeBannerItemRequest request) {
        try {
            final HomeBannerItem item = bannerService.updateHomeBannerItem(request);
            item.setHomeBanner(null);
            return makeResponse(0, "ok", item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @DeleteMapping(path = "/api/v2/admin/home_banner_item/{id}")
    public Map<String, Object> deleteHomeBannerItem(@PathVariable(name = "id") Integer id) {
        try {
            final HomeBannerItem item = bannerService.deleteHomeBannerItem(id);
            return makeResponse(0, "ok", item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @PostMapping(path = "/api/v2/admin/holiday_banner")
    public Map<String, Object> createHolidayBanner(@RequestBody HolidayBannerRequest request) {
        try {
            final HolidayBanner holidayBanner = bannerService.createHolidayBanner(request);
            return makeResponse(0, "ok", holidayBanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @PutMapping(path = "/api/v2/admin/holiday_banner")
    public Map<String, Object> updateHolidayBanner(@RequestBody HolidayBannerRequest request) {
        try {
            final HolidayBanner holidayBanner = bannerService.updateHolidayBanner(request);
            return makeResponse(0, "ok", holidayBanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @DeleteMapping(path = "/api/v2/admin/holiday_banner/{id}")
    public Map<String, Object> deleteHolidayBanner(@PathVariable(name = "id") Integer id) {
        try {
            final HolidayBanner holidayBanner = bannerService.deleteHolidayBanner(id);
            return makeResponse(0, "ok", holidayBanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @PostMapping(path = "/api/v2/admin/event_banner")
    public Map<String, Object> newEventBanner(@RequestBody EventBannerRequest request) {
        try {
            final EventBanner eventBanner = bannerService.createEventBanner(request);
            return makeResponse(0, "ok", eventBanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @PutMapping(path = "/api/v2/admin/event_banner")
    public Map<String, Object> updateEventBanner(@RequestBody EventBannerRequest request) {
        try {
            final EventBanner banner = bannerService.updateEventBanner(request);
            return makeResponse(0, "ok", banner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }

    @DeleteMapping(path = "/api/v2/admin/event_banner/{id}")
    public Map<String, Object> newEventBanner(@PathVariable(name = "id") Integer id) {
        try {
            final EventBanner banner = bannerService.deleteEventBanner(id);
            return makeResponse(0, "ok", banner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResponse(1, "error", null);
    }


}
