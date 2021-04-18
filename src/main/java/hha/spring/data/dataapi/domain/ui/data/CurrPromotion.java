package hha.spring.data.dataapi.domain.ui.data;


import java.util.List;

/**
 * The type Curr promotion.
 */
public class CurrPromotion {

  private Integer eventId;
  private String description;
  private String bannerImageUrl;

  private List<CurrPromotionProduct> products;

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public Integer getEventId() {
    return eventId;
  }

    /**
     * Sets event id.
     *
     * @param eventId the event id
     */
    public void setEventId(Integer eventId) {
    this.eventId = eventId;
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

    /**
     * Gets products.
     *
     * @return the products
     */
    public List<CurrPromotionProduct> getProducts() {
    return products;
  }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(List<CurrPromotionProduct> products) {
    this.products = products;
  }

    /**
     * Instantiates a new Curr promotion.
     */
    public CurrPromotion() {
  }

    /**
     * Instantiates a new Curr promotion.
     *
     * @param eventId        the event id
     * @param description    the description
     * @param bannerImageUrl the banner image url
     * @param products       the products
     */
    public CurrPromotion(Integer eventId, String description, String bannerImageUrl, List<CurrPromotionProduct> products) {
    this.eventId = eventId;
    this.description = description;
    this.bannerImageUrl = bannerImageUrl;
    this.products = products;
  }

  @Override
  public String toString() {
    return "CurrentPromotion{" +
            "eventId=" + eventId +
            ", description='" + description + '\'' +
            ", bannerImageUrl='" + bannerImageUrl + '\'' +
            ", products=" + products +
            '}';
  }
}
