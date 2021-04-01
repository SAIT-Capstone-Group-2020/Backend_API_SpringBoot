package hha.spring.data.dataapi.domain.ui.data;


public class CurrHomeBanner {

  private String title;
  private String description;
  private String bannerImageUrl;

  public CurrHomeBanner() {
  }

  public CurrHomeBanner(String title, String description, String bannerImageUrl) {
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
