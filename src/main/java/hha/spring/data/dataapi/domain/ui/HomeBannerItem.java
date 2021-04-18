package hha.spring.data.dataapi.domain.ui;

import javax.persistence.*;

/**
 * The type Home banner item.
 */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets home banner.
     *
     * @return the home banner
     */
    public HomeBanner getHomeBanner() {
        return homeBanner;
    }

    /**
     * Sets home banner.
     *
     * @param homeBanner the home banner
     */
    public void setHomeBanner(HomeBanner homeBanner) {
        this.homeBanner = homeBanner;
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
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
