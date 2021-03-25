package hha.spring.data.dataapi.domain.ui;


import javax.persistence.*;

@Entity
@Table(name = "banner_type")
public class BannerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_type_id")
    private Integer bannerTypeId;
    
    @Column(name = "banner_type")
    private String bannerType;


    public Integer getBannerTypeId() {
        return bannerTypeId;
    }

    public void setBannerTypeId(Integer bannerTypeId) {
        this.bannerTypeId = bannerTypeId;
    }


    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

}
