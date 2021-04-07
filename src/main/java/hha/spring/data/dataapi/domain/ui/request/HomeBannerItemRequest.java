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

    public String getImageFileExtension() {
        return imageFileExtension;
    }

    public void setImageFileExtension(String imageFileExtension) {
        this.imageFileExtension = imageFileExtension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeBannerId() {
        return homeBannerId;
    }

    public void setHomeBannerId(Integer homeBannerId) {
        this.homeBannerId = homeBannerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
