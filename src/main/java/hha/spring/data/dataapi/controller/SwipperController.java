package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.Swipper;
import hha.spring.data.dataapi.repository.SwipperRepository;
import hha.spring.data.dataapi.service.SwipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * controller for get all information for swipper ui control in front-end
 */
@RestController
public class SwipperController {
    @Autowired
    private SwipperService swipperService;

    /**
     * if you want get all banner in a swipper at home page.
     * using https://xxx.heroku.com/api/v2/ui/swipper/home
     *
     * @return
     */
    @GetMapping("/api/v2/ui/swipper/{type}")
    public Swipper getSwipper(@PathVariable String type) {
        try {
            final Swipper homeSwipper = swipperService.getHomeSwipper();
            if (homeSwipper.getBanners()!=null) {
                for (Banner banner : homeSwipper.getBanners()) {
                    banner.setSwipper(null);
                }
            }
            return homeSwipper;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }
    }


    @PostMapping("/api/v2/ui/swipper")
    public void postSwipper(@RequestBody Swipper swipper) {
        System.out.println(swipper);
        try {
            swipperService.saveSwipper(swipper);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }
}
