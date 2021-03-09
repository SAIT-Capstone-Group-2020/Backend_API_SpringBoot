package hha.spring.data.dataapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.BannerItem;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.BannerItemRepository;
import hha.spring.data.dataapi.repository.BannerRepository;

@Service
@Transactional
public class BannerService {

    @Autowired
    private BannerItemRepository bannerDao;


    @Autowired
    private BannerRepository bannerRepository;

    public List<BannerItem> getAllBanner() {
        return bannerDao.getAllBannerInfo();
    }


    public Banner findByUrl(String url) {
        return bannerRepository.findByUrl(url);
    }


    public String addBanner(Banner banner) {
        try {
            bannerRepository.save(banner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return "Successfully added";

    }


    public Banner getBannerById(int id) {
        return bannerRepository.getOne(id);
    }


    public void saveBanner(Banner banner) {
        bannerRepository.save(banner);
    }


	public void deleteBanner(int id) {
		bannerRepository.deleteById(id);
	}



}
