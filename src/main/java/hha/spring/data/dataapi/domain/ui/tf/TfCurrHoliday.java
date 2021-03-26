package hha.spring.data.dataapi.domain.ui.tf;


public class TfCurrHoliday {
  public TfCurrHoliday() {
  }

  public TfCurrHoliday(String bannerImageUrl) {
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
