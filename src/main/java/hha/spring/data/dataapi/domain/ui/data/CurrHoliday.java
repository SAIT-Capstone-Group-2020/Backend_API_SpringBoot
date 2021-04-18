package hha.spring.data.dataapi.domain.ui.data;


/**
 * The type Curr holiday.
 */
public class CurrHoliday {
    /**
     * Instantiates a new Curr holiday.
     */
    public CurrHoliday() {
  }

    /**
     * Instantiates a new Curr holiday.
     *
     * @param bannerImageUrl the banner image url
     */
    public CurrHoliday(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

  private String bannerImageUrl;

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
