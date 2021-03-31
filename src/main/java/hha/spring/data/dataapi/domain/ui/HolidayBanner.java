package hha.spring.data.dataapi.domain.ui;

import javax.persistence.*;

@Entity
@Table(name = "holiday_banner")
public class HolidayBanner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "holiday_banner_id")
  private Integer holidayBannerId;

  @Basic
  @Column(name = "banner_image_url")
  private String bannerImageUrl;

  @Basic
  @Column(name = "begin_date")
  private java.sql.Date beginDate;

  @Basic
  @Column(name = "end_date")
  private java.sql.Date endDate;
  @Basic
  @Column(name = "comment")
  private String comment;


  public Integer getHolidayBannerId() {
    return holidayBannerId;
  }

  public void setHolidayBannerId(Integer holidayBannerId) {
    this.holidayBannerId = holidayBannerId;
  }


  public String getBannerImageUrl() {
    return bannerImageUrl;
  }

  public void setBannerImageUrl(String bannerImageUrl) {
    this.bannerImageUrl = bannerImageUrl;
  }


  public java.sql.Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(java.sql.Date beginDate) {
    this.beginDate = beginDate;
  }


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

}
