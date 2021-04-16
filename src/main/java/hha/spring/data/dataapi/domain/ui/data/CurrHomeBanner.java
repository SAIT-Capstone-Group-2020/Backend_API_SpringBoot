package hha.spring.data.dataapi.domain.ui.data;


/**
 * The type Curr home banner.
 */
public class CurrHomeBanner {

  private String title;
  private String description;
  private String bannerImageUrl;

    /**
     * Instantiates a new Curr home banner.
     */
    public CurrHomeBanner() {
  }

    /**
     * Instantiates a new Curr home banner.
     *
     * @param title          the title
     * @param description    the description
     * @param bannerImageUrl the banner image url
     */
    public CurrHomeBanner(String title, String description, String bannerImageUrl) {
    this.title = title;
    this.description = description;
    this.bannerImageUrl = bannerImageUrl;
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


    /**
     * Gets banner image url.
     *
     * @return the banner image url
     */
    public String getBannerImageUrl() {
    return bannerImageUrl;
  }

    /**
     * Sets banner image url.
     *
     * @param bannerImageUrl the banner image url
     */
    public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
