package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.repository.HomeBannerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeBannerService {
    private HomeBannerRepository homeBannerRepository;

    public HomeBannerService(HomeBannerRepository homeBannerRepository) {
        this.homeBannerRepository = homeBannerRepository;
    }

    public List<HomeBanner> getAllHomeBanner() {
        final Iterable<HomeBanner> all = homeBannerRepository.findAll();
        ArrayList<HomeBanner> homeBanners = new ArrayList<>();
        for (HomeBanner homeBanner : all) {
            homeBanners.add(homeBanner);
        }
        return homeBanners;
    }
}
