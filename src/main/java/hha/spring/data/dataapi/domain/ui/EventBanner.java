package hha.spring.data.dataapi.domain.ui;


import hha.spring.data.dataapi.domain.event.Event;

import javax.persistence.*;

@Entity
@Table(name = "event_banner")
public class EventBanner {

    @Id
    @Column(name = "event_id",nullable = false)
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "event_id",referencedColumnName = "event_id")
    private Event event;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;

    @Basic
    @Column(name = "comment")
    private String comment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
