package hha.spring.data.dataapi.domain.ui.request;

/**
 * The type Event banner request.
 */
public class EventBannerRequest {
    private Integer id;
    private String image;
    private String imageExtension;
    private String comment;

    /**
     * Gets image extension.
     *
     * @return the image extension
     */
    public String getImageExtension() {
        return imageExtension;
    }

    /**
     * Sets image extension.
     *
     * @param imageExtension the image extension
     */
    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
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
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
