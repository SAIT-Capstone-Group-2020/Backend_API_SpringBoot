package hha.spring.data.dataapi.service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
=======
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> 01bda963616f4e6a2ae5af778129d61b8a33418e
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.BannerItem;
import hha.spring.data.dataapi.repository.BannerItemRepository;
import hha.spring.data.dataapi.repository.BannerRepository;

public class BannerService {

    @Autowired
    private BannerItemRepository bannerDao;

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private BannerRepository bannerRepository;
<<<<<<< HEAD

    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }
=======
    /*
    public List<BannerItem> getAllBanner() {
        return bannerDao.getAllBannerInfo();
    }*/
>>>>>>> 01bda963616f4e6a2ae5af778129d61b8a33418e

    @Autowired
    private BannerItemRepository bannerItemRepository;


    @Transactional
    public String addBanner(Banner banner) {
        try {
            final List<BannerItem> bannerItems = banner.getBannerItems();
            for (BannerItem bannerItem : bannerItems) {
                bannerItem.setBanner(banner);
            }
            bannerRepository.save(banner);
            if (bannerItems != null) {
                for (BannerItem item : bannerItems) {
                    final UUID uuid = UUID.randomUUID();
                    String fileName = uuid.toString() + item.getFileName().substring(item.getFileName().lastIndexOf("."));
                    item.setObjectId(fileName);
                    item.setUrl("https://hhamedia.s3.us-east-2.amazonaws.com/" + fileName);
                    awsS3Service.upload(fileName, Base64.getDecoder().decode(item.getData()));
                    item.setBanner(banner);
                    bannerItemRepository.save(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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


<<<<<<< HEAD
    public void deleteBanner(int id) {
        bannerRepository.deleteById(id);
    }

=======
>>>>>>> 01bda963616f4e6a2ae5af778129d61b8a33418e
}
