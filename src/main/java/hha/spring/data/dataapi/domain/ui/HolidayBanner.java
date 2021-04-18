package hha.spring.data.dataapi.domain.ui;

import javax.persistence.*;

/**
 * The type Holiday banner.
 */
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


    /**
     * Gets holiday banner id.
     *
     * @return the holiday banner id
     */
    public Integer getHolidayBannerId() {
    return holidayBannerId;
  }

    /**
     * Sets holiday banner id.
     *
     * @param holidayBannerId the holiday banner id
     */
    public void setHolidayBannerId(Integer holidayBannerId) {
    this.holidayBannerId = holidayBannerId;
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
     * Gets begin date.
     *
     * @return the begin date
     */
    public java.sql.Date getBeginDate() {
    return beginDate;
  }

    /**
     * Sets begin date.
     *
     * @param beginDate the begin date
     */
    public void setBeginDate(java.sql.Date beginDate) {
    this.beginDate = beginDate;
  }


    /**
     * Gets end date.
     *
     * @return the end date
     */
    public java.sql.Date getEndDate() {
    return endDate;
  }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
    return comment;
  }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
    this.comment = comment;
  }

}
