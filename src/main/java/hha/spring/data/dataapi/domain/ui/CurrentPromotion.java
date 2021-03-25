package hha.spring.data.dataapi.domain.ui;


public class CurrentPromotion {

  private Integer productId;
  private String productName;
  private double retailPrice;
  private double discountPrice;
  private String bannerImageUrl;

  public CurrentPromotion(Integer productId, String productName, double retailPrice, double discountPrice, String bannerImageUrl) {
    this.productId = productId;
    this.productName = productName;
    this.retailPrice = retailPrice;
    this.discountPrice = discountPrice;
    this.bannerImageUrl = bannerImageUrl;
  }

  public CurrentPromotion() {
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


  public double getRetailPrice() {
    return retailPrice;
  }

  public void setRetailPrice(double retailPrice) {
    this.retailPrice = retailPrice;
  }


  public double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(double discountPrice) {
    this.discountPrice = discountPrice;
  }


  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
