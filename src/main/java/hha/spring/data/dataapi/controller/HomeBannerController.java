package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.service.HomeBannerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class HomeBannerController {
    private HomeBannerService homeBannerService;

    public HomeBannerController(HomeBannerService homeBannerService) {
        this.homeBannerService = homeBannerService;
    }

    @GetMapping("/api/v2/ui/all_banner")
    public List<HomeBanner> httpGetAllBanner() {

        try {
            return this.homeBannerService.getAllHomeBanner();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }
    }
}
