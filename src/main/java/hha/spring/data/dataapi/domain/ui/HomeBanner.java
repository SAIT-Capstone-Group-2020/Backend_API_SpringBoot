package hha.spring.data.dataapi.domain.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "home_banner")
public class HomeBanner {
    @Id
    @Column(name = "home_banner_id")
    public Integer id;
    @Basic
    public Integer event_id;
    @Basic
    public String banner_image_url;
    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Integer banner_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getBanner_image_url() {
        return banner_image_url;
    }

    public void setBanner_image_url(String banner_image_url) {
        this.banner_image_url = banner_image_url;
    }

    public Integer getBanner_type() {
        return banner_type;
    }

    public void setBanner_type(Integer banner_type) {
        this.banner_type = banner_type;
    }
}
