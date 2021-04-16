package hha.spring.data.dataapi.domain.ui.request;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The type Holiday banner request.
 */
public class HolidayBannerRequest {
    private Integer holidayBannerId;
    private String bannerImage;
    private String imageExtension;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date endDate;
    private String comment;


    /**
     * Gets image extension.
     *
     * @return the image extension
     */
    public String getImageExtension() {
        return imageExtension;
    }

    /**
     * Sets image extension.
     *
     * @param imageExtension the image extension
     */
    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

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
     * Gets banner image.
     *
     * @return the banner image
     */
    public String getBannerImage() {
        return bannerImage;
    }

    /**
     * Sets banner image.
     *
     * @param bannerImage the banner image
     */
    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
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
