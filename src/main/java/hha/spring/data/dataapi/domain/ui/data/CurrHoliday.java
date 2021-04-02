package hha.spring.data.dataapi.domain.ui.data;


public class CurrHoliday {
  public CurrHoliday() {
  }

  public CurrHoliday(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

  private String bannerImageUrl;

  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }

}
