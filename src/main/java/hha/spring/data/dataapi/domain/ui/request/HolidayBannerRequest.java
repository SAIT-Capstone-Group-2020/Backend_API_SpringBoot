package hha.spring.data.dataapi.domain.ui.request;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HolidayBannerRequest {
    private Integer holidayBannerId;
    private String bannerImage;
    private String imageExtension;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date endDate;
    private String comment;


    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public Integer getHolidayBannerId() {
        return holidayBannerId;
    }

    public void setHolidayBannerId(Integer holidayBannerId) {
        this.holidayBannerId = holidayBannerId;
    }


    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
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
