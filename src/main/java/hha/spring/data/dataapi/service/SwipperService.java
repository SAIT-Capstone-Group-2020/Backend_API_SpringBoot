package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.Swipper;
import hha.spring.data.dataapi.repository.BannerRepository;
import hha.spring.data.dataapi.repository.SwipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Base64;
import java.util.UUID;

@Service
public class SwipperService {
    @Autowired
    SwipperRepository swipperRepository;
    @Autowired
    BannerRepository bannerRepository;
    @Autowired
    AwsS3Service awsS3Service;

    @Transactional
    public void saveSwipper(Swipper swipper) {
        swipperRepository.save(swipper);
        for (Banner item : swipper.getBanners()) {
            final UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString() + item.getFileName().substring(item.getFileName().lastIndexOf("."));
            item.setObjectId(fileName);
            item.setUrl("https://" + awsS3Service.getBucketName() + ".s3." + awsS3Service.getRegionName() + ".amazonaws.com/" + fileName);
            awsS3Service.upload(fileName, Base64.getDecoder().decode(item.getData()));
            item.setBanner(swipper);
            bannerRepository.save(item);
        }
    }

    public Swipper getHomeSwipper() {
        final Swipper home = swipperRepository.findFirstByTypeEqualsOrderByIdDesc("home");
        return home;
    }

    public Swipper getXXXSwiper() {
        throw new NotImplementedException();
    }

}
