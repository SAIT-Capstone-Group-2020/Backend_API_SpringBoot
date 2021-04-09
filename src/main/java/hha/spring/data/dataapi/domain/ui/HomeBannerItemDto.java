package hha.spring.data.dataapi.domain.ui;

import javax.persistence.*;

@Entity
@Table(name = "home_banner_item")
public class HomeBannerItemDto {

    @Id
    @Column(name = "home_banner_item_id")
    private Integer id;

    @Column(name = "home_banner_id")
    private Integer home_id;

    @Column(name = "banner_image_url")
    private String imageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public Integer getHome_id() {
        return home_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
