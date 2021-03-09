package hha.spring.data.dataapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Home_banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_banner_id")
    private int id;

    @Column(name = "event_id")
    private int event_id;

    @Column(name = "banner_image_url")
    private String url;


    @Column(name = "banner_type")
    private int type;


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return the event_id
     */
    public int getEvent_id() {
        return event_id;
    }


    /**
     * @param event_id the event_id to set
     */
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }


    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }


    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * @return the type
     */
    public int getType() {
        return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
