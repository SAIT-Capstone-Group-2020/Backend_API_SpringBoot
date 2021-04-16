package hha.spring.data.dataapi.domain.event;

import java.util.Date;
import java.util.List;

/**
 * This class is a data object that works as response entity
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
public class EventDto {

    private int event_id;
    private String event_title;
    private Date start_date;
    private Date end_date;
    private String description;
    private List<EventItemDto> itemList;

    /**
     * Instantiates a new Event dto.
     *
     * @param event_id    the event id
     * @param event_title the event title
     * @param start_date  the start date
     * @param end_date    the end date
     * @param description the description
     * @param itemList    the item list
     */
    public EventDto(int event_id, String event_title, Date start_date, Date end_date, String description, List<EventItemDto> itemList) {
        this.event_id = event_id;
        this.event_title = event_title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.itemList = itemList;
    }

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public int getEvent_id() {
        return event_id;
    }

    /**
     * Gets event title.
     *
     * @return the event title
     */
    public String getEvent_title() {
        return event_title;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStart_date() {
        return start_date;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets item list.
     *
     * @return the item list
     */
    public List<EventItemDto> getItemList() {
        return itemList;
    }
}
