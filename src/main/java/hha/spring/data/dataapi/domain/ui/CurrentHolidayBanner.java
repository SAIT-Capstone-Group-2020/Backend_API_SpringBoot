package hha.spring.data.dataapi.domain.ui;


public class CurrentHolidayBanner {

  private Integer productId;
  private String bannerImageUrl;

  public CurrentHolidayBanner() {
  }

  public CurrentHolidayBanner(Integer productId, String bannerImageUrl) {
    this.productId = productId;
    this.bannerImageUrl = bannerImageUrl;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }


  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
