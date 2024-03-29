package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.ui.*;
import hha.spring.data.dataapi.domain.ui.request.EventBannerRequest;
import hha.spring.data.dataapi.domain.ui.request.HolidayBannerRequest;
import hha.spring.data.dataapi.domain.ui.request.HomeBannerItemRequest;
import hha.spring.data.dataapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * controller for get all information for swipper ui control in front-end
 * detail request parameter see endpoints.xlsx pls.
 */
@CrossOrigin
@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * get all the banner info
     *
     * @return banner info
     */
    @GetMapping("/api/v2/ui/allbanner")
    public HashMap<String, Object> getAllBanner() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("home", bannerService.findCurrentHomeBanner());
        map.put("holiday", bannerService.findCurrentHolidayBanner());
        map.put("promotion", bannerService.findCurrentPromotion());
        return map;
    }

    /**
     * process server response
     * @param code response code
     * @param msg response message
     * @param data response data
     * @return
     */
    private Map<String, Object> makeResponse(int code, String msg, Object data) {
        final HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    /**
     * New home banner map.
     *
     * @param homeBanner the home banner
     * @return map map
     */
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

    /**
     * Update home banner map.
     *
     * @param homeBanner the home banner
     * @return the map
     */
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

    /**
     * delete the banner with the specified ID
     *
     * @param id banner id
     * @return map map
     */
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

    /**
     * create a new banner
     *
     * @param request delivering banner messages
     * @return map map
     */
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

    /**
     * update the specified banner info
     *
     * @param request the request
     * @return map map
     */
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

    /**
     * delete the banner item with the specified ID
     *
     * @param id banner id
     * @return map map
     */
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

    /**
     * create a holiday banner
     *
     * @param request holiday banner info
     * @return map map
     */
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

    /**
     * update holiday banner info
     *
     * @param request hoiliday banner info
     * @return map map
     */
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

    /**
     * delete the holiday banner with the specified ID
     *
     * @param id the id
     * @return map map
     */
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

    /**
     * create a new event holiday banner
     *
     * @param request event banner info
     * @return map map
     */
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

    /**
     * update evnet banner
     *
     * @param request event banner info
     * @return map map
     */
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

    /**
     * New event banner map.
     *
     * @param id the id
     * @return the map
     */
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

    /**
     * get all the event banner info
     *
     * @return all event banner
     */
    @GetMapping("/api/v2/admin/event_banner")
    public List<EventBanner> getAllEventBanner() {
        return bannerService.getAllEventBanner();
    }

    /**
     * get all the holiday banner info
     *
     * @return all holiday banner
     */
    @GetMapping("/api/v2/admin/holiday_banner")
    public List<HolidayBanner> getAllHolidayBanner() {
        return bannerService.getAllHolidayBanner();
    }


    /**
     * get all the home banner info
     *
     * @return all home banner
     */
    @GetMapping("/api/v2/admin/home_banner")
    public List<HomeBannerItemDto> getAllHomeBanner() {
        return bannerService.getAllHomeBannerItem();
    }

}
