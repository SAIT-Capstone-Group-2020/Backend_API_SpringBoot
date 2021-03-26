package hha.spring.data.dataapi.domain.ui.tf;


public class TfCurrHomeBanner {

  private String title;
  private String description;
  private String bannerImageUrl;

  public TfCurrHomeBanner() {
  }

  public TfCurrHomeBanner(String title, String description, String bannerImageUrl) {
    this.title = title;
    this.description = description;
    this.bannerImageUrl = bannerImageUrl;
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


  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
