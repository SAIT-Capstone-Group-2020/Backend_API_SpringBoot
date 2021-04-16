package hha.spring.data.dataapi.domain.ui;

import javax.persistence.*;

/**
 * The type Home banner item dto.
 */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets home id.
     *
     * @return the home id
     */
    public Integer getHome_id() {
        return home_id;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
