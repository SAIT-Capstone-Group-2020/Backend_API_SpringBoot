package hha.spring.data.dataapi.domain.ui;

import javax.persistence.*;

@Entity
@Table(name = "home_banner_item")
public class HomeBannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_banner_item_id")
    private Integer id;

    @ManyToOne(targetEntity = HomeBanner.class, optional = false)
    @JoinColumn(name = "home_banner_id")
    private HomeBanner homeBanner;

    @Basic
    @Column(name = "banner_image_url")
    private String imageUrl;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HomeBanner getHomeBanner() {
        return homeBanner;
    }

    public void setHomeBanner(HomeBanner homeBanner) {
        this.homeBanner = homeBanner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
