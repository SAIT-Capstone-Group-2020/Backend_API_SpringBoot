package hha.spring.data.dataapi.domain.ui.request;

/**
 * Data received from client
 */
public class HomeBannerItemRequest {
    private Integer id;
    private Integer homeBannerId;
    private String image;
    private String imageFileExtension;
    private String title;
    private String description;

    /**
     * Gets image file extension.
     *
     * @return the image file extension
     */
    public String getImageFileExtension() {
        return imageFileExtension;
    }

    /**
     * Sets image file extension.
     *
     * @param imageFileExtension the image file extension
     */
    public void setImageFileExtension(String imageFileExtension) {
        this.imageFileExtension = imageFileExtension;
    }

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
     * Gets home banner id.
     *
     * @return the home banner id
     */
    public Integer getHomeBannerId() {
        return homeBannerId;
    }

    /**
     * Sets home banner id.
     *
     * @param homeBannerId the home banner id
     */
    public void setHomeBannerId(Integer homeBannerId) {
        this.homeBannerId = homeBannerId;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
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
