package hha.spring.data.dataapi.domain.ui;


public class CurrentHomeBanner {

  private Integer productId;
  private String productName;
  private String description;
  private String bannerImageUrl;

  public CurrentHomeBanner(Integer productId, String productName, String description, String bannerImageUrl) {
    this.productId = productId;
    this.productName = productName;
    this.description = description;
    this.bannerImageUrl = bannerImageUrl;
  }

  public CurrentHomeBanner() {
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
