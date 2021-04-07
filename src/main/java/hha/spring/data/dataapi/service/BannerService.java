package hha.spring.data.dataapi.service;


import hha.spring.data.dataapi.domain.event.Event;
import hha.spring.data.dataapi.domain.ui.EventBanner;
import hha.spring.data.dataapi.domain.ui.HolidayBanner;
import hha.spring.data.dataapi.domain.ui.HomeBanner;
import hha.spring.data.dataapi.domain.ui.HomeBannerItem;
import hha.spring.data.dataapi.domain.ui.data.CurrPromotion;
import hha.spring.data.dataapi.domain.ui.data.CurrHoliday;
import hha.spring.data.dataapi.domain.ui.data.CurrHomeBanner;
import hha.spring.data.dataapi.domain.ui.request.EventBannerRequest;
import hha.spring.data.dataapi.domain.ui.request.HolidayBannerRequest;
import hha.spring.data.dataapi.domain.ui.request.HomeBannerItemRequest;
import hha.spring.data.dataapi.mapper.PromotionMapper;
import hha.spring.data.dataapi.repository.EventBannerRepository;
import hha.spring.data.dataapi.repository.HolidayBannerRepository;
import hha.spring.data.dataapi.repository.HomeBannerItemRepository;
import hha.spring.data.dataapi.repository.HomeBannerRepository;
import hha.spring.data.dataapi.repository.event.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BannerService {
    private HomeBannerRepository homeBannerRepository;
    private PromotionMapper promotionMapper;
    private AwsS3Service awsS3Service;
    private HomeBannerItemRepository homeBannerItemRepository;
    private HolidayBannerRepository holidayBannerRepository;
    private EventBannerRepository eventBannerRepository;
    private EventRepository eventRepository;

    public BannerService(HomeBannerRepository homeBannerRepository, PromotionMapper promotionMapper, AwsS3Service awsS3Service, HomeBannerItemRepository homeBannerItemRepository, HolidayBannerRepository holidayBannerRepository, EventBannerRepository eventBannerRepository, EventRepository eventRepository) {
        this.homeBannerRepository = homeBannerRepository;
        this.promotionMapper = promotionMapper;
        this.awsS3Service = awsS3Service;
        this.homeBannerItemRepository = homeBannerItemRepository;
        this.holidayBannerRepository = holidayBannerRepository;
        this.eventBannerRepository = eventBannerRepository;
        this.eventRepository = eventRepository;
    }


    @Transactional
    public EventBanner updateEventBanner(EventBannerRequest request) {
        if (request != null && request.getId() != null) {
            final Optional<EventBanner> eventBannerOptional = eventBannerRepository.findById(request.getId());
            if (eventBannerOptional.isPresent()) {
                final EventBanner eventBanner = eventBannerOptional.get();
                if (request.getImage() != null && request.getImageExtension() != null) {
                    awsS3Service.delete(eventBanner.getImageUrl().substring(eventBanner.getImageUrl().lastIndexOf("/") + 1));
                    final String key = randomAwsKey(request.getImageExtension());
                    awsS3Service.upload(key, Base64.getDecoder().decode(request.getImage()));
                    eventBanner.setImageUrl(awsS3Service.getObjectURL(key));
                }
                if (request.getComment() != null) {
                    eventBanner.setComment(request.getComment());
                }
                eventBannerRepository.save(eventBanner);
                return eventBanner;
            }
        }
        return null;
    }


    @Transactional
    public EventBanner deleteEventBanner(Integer id) {
        final Optional<EventBanner> byId = eventBannerRepository.findById(id);
        if (byId.isPresent()) {
            eventBannerRepository.delete(byId.get());
            return byId.get();
        }
        return null;
    }

    @Transactional
    public EventBanner createEventBanner(EventBannerRequest request) {
        if (request.getId() != null && request.getImage() != null && request.getImageExtension() != null) {
            EventBanner banner = new EventBanner();
            banner.setId(request.getId());
            banner.setComment(request.getComment());
            final Optional<Event> event = eventRepository.findById(request.getId());
            if (event.isPresent()) {
                final String key = randomAwsKey(request.getImageExtension());
                awsS3Service.upload(key, Base64.getDecoder().decode(request.getImage()));
                banner.setImageUrl(awsS3Service.getObjectURL(key));
                eventBannerRepository.save(banner);
                return banner;
            }
        }
        return null;
    }


    @Transactional
    public HolidayBanner updateHolidayBanner(HolidayBannerRequest request) {
        if (request != null && request.getHolidayBannerId() != null) {
            final Optional<HolidayBanner> banner = holidayBannerRepository.findById(request.getHolidayBannerId());
            if (banner.isPresent()) {
                final HolidayBanner holidayBanner = banner.get();
                if (request.getBannerImage() != null && request.getImageExtension() != null) {
                    awsS3Service.delete(holidayBanner.getBannerImageUrl().substring(holidayBanner.getBannerImageUrl().lastIndexOf("/") + 1));
                    final String key = randomAwsKey(request.getImageExtension());
                    awsS3Service.upload(key, Base64.getDecoder().decode(request.getBannerImage()));
                    holidayBanner.setBannerImageUrl(awsS3Service.getObjectURL(key));
                }
                if (request.getBeginDate() != null) {
                    holidayBanner.setBeginDate(request.getBeginDate());
                }
                if (request.getEndDate() != null) {
                    holidayBanner.setEndDate(request.getEndDate());
                }
                if (request.getComment() != null) {
                    holidayBanner.setComment(request.getComment());
                }
                holidayBannerRepository.save(holidayBanner);
                return holidayBanner;
            }
        }
        return null;
    }

    @Transactional
    public HolidayBanner deleteHolidayBanner(Integer id) {
        final Optional<HolidayBanner> holidayBanner = holidayBannerRepository.findById(id);
        if (holidayBanner.isPresent()) {
            final HolidayBanner banner1 = holidayBanner.get();
            holidayBannerRepository.delete(banner1);
            return banner1;
        }
        return null;
    }

    @Transactional
    public HolidayBanner createHolidayBanner(HolidayBannerRequest request) {
        if (request == null
                || request.getBannerImage() == null
                || request.getBeginDate() == null
                || request.getEndDate() == null
                || request.getImageExtension() == null) {
            return null;
        } else {
            final HolidayBanner holidayBanner = new HolidayBanner();
            final String key = randomAwsKey(request.getImageExtension());
            awsS3Service.upload(key, Base64.getDecoder().decode(request.getBannerImage()));
            holidayBanner.setBannerImageUrl(awsS3Service.getObjectURL(key));
            holidayBanner.setBeginDate(request.getBeginDate());
            holidayBanner.setEndDate(request.getEndDate());
            holidayBanner.setComment(request.getComment());
            holidayBannerRepository.save(holidayBanner);
            return holidayBanner;
        }
    }

    private static final String randomAwsKey(String extension) {
        return UUID.randomUUID().toString() + extension;
    }

    @Transactional
    public HomeBannerItem updateHomeBannerItem(HomeBannerItemRequest request) {
        final Optional<HomeBannerItem> homeBannerItem = homeBannerItemRepository.findById(request.getId());
        if (homeBannerItem.isPresent()) {
            final HomeBannerItem item = homeBannerItem.get();

            //if new image uploaded
            if (request.getImage() != null && request.getImageFileExtension() != null) {
                final String imageUrl = item.getImageUrl();
                final String id = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                try {
                    awsS3Service.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String nid = UUID.randomUUID().toString() + request.getImageFileExtension();
                final byte[] images = Base64.getDecoder().decode(request.getImage());
                awsS3Service.upload(nid, images);
                final String imageURL = awsS3Service.getObjectURL(nid);
                item.setImageUrl(imageURL);
            }
            if (request.getTitle() != null) {
                item.setTitle(request.getTitle());
            }
            if (request.getDescription() != null) {
                item.setDescription(request.getDescription());
            }
            homeBannerItemRepository.save(item);
            return item;
        }
        return null;
    }


    @Transactional
    public HomeBannerItem deleteHomeBannerItem(Integer homeBannerItemId) {
        final Optional<HomeBannerItem> homeBannerItem = homeBannerItemRepository.findById(homeBannerItemId);
        if (homeBannerItem.isPresent()) {
            final HomeBannerItem item = homeBannerItem.get();
            item.getHomeBanner().removeHomeBannerItem(item);
            return item;
        } else {
            return null;
        }
    }

    /**
     * create home banner item
     *
     * @param request the home banner item request
     * @return the saved home banner
     */
    public HomeBannerItem createHomeBannerItem(HomeBannerItemRequest request) {
        if (request == null || request.getImage() == null || request.getImageFileExtension() == null || request.getHomeBannerId() == null) {
            return null;
        }
        final String id = UUID.randomUUID().toString() + request.getImageFileExtension();
        final byte[] images = Base64.getDecoder().decode(request.getImage());
        awsS3Service.upload(id, images);
        final String imageURL = awsS3Service.getObjectURL(id);
        final HomeBannerItem item = new HomeBannerItem();
        item.setImageUrl(imageURL);
        item.setDescription(request.getDescription());
        item.setTitle(request.getTitle());
        return createHomeBannerItemAux(request.getHomeBannerId(), item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public HomeBannerItem createHomeBannerItemAux(Integer homeBannerId, HomeBannerItem item) {
        final Optional<HomeBanner> homeBanner = homeBannerRepository.findById(homeBannerId);
        if (homeBanner.isPresent()) {
            final HomeBanner banner = homeBanner.get();
            banner.addHomeBannerItem(item);
            homeBannerItemRepository.save(item);
            return item;
        } else {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createHomeBanner(HomeBanner homeBanner) {
        homeBannerRepository.save(homeBanner);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public HomeBanner updateHomeBanner(HomeBanner homeBanner) {
        final Optional<HomeBanner> old = homeBannerRepository.findById(homeBanner.getHomeBannerId());
        if (old.isPresent()) {
            final HomeBanner banner = old.get();
            banner.setEndDate(homeBanner.getEndDate());
            banner.setStartDate(homeBanner.getStartDate());
            banner.setComment(homeBanner.getComment());
            homeBannerRepository.save(banner);
            return banner;
        } else {
            return null;
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public HomeBanner deleteHomeBanner(Integer id) {
        final Optional<HomeBanner> homeBanner = homeBannerRepository.findById(id);
        if (homeBanner.isPresent()) {
            final HomeBanner banner = homeBanner.get();
            homeBannerRepository.delete(banner);
            return banner;
        } else {
            return null;
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CurrHoliday findCurrentHolidayBanner() {
        return homeBannerRepository.queryCurrentHolidayBanner();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<CurrHomeBanner> findCurrentHomeBanner() {
        return homeBannerRepository.queryCurrentHomeBanner();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CurrPromotion findCurrentPromotion() {
        return promotionMapper.currentWeeklyPromotion();
    }


}

