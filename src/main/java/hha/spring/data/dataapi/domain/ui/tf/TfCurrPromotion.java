package hha.spring.data.dataapi.domain.ui.tf;


public class TfCurrPromotion {

  private Integer productId;
  private String productName;
  private Double retailPrice;
  private Double discountPrice;
  private String bannerImageUrl;


  public TfCurrPromotion() {
  }

  public TfCurrPromotion(Integer productId, String productName, Double retailPrice, Double discountPrice, String bannerImageUrl) {
    this.productId = productId;
    this.productName = productName;
    this.retailPrice = retailPrice;
    this.discountPrice = discountPrice;
    this.bannerImageUrl = bannerImageUrl;
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


  public Double getRetailPrice() {
    return retailPrice;
  }

  public void setRetailPrice(Double retailPrice) {
    this.retailPrice = retailPrice;
  }


  public Double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(Double discountPrice) {
    this.discountPrice = discountPrice;
  }


  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
