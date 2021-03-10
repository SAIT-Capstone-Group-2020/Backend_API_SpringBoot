package hha.spring.data.dataapi.domain.event;

import java.util.Date;
import java.util.List;

public class EventDto {

    private int event_id;
    private String event_title;
    private Date start_date;
    private Date end_date;
    private String description;
    private List<EventItemDto> itemList;

    public EventDto(int event_id, String event_title, Date start_date, Date end_date, String description, List<EventItemDto> itemList) {
        this.event_id = event_id;
        this.event_title = event_title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.itemList = itemList;
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getDescription() {
        return description;
    }

    public List<EventItemDto> getItemList() {
        return itemList;
    }
}
