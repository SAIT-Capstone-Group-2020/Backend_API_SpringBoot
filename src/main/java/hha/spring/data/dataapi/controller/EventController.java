package hha.spring.data.dataapi.controller;

import java.util.NoSuchElementException;
import hha.spring.data.dataapi.domain.event.Discount;
import hha.spring.data.dataapi.domain.event.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import hha.spring.data.dataapi.domain.event.Event;
import hha.spring.data.dataapi.service.EventService;

/**
 * This class is a Spring controller which serializes
 * every 'event' & 'discount' table related request handling methods.
 * This controller uses EventService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	/**
	 * delete a event
	 * Authorization header needed(JWT token)
	 *
	 * @param id - integer value(id of the event)
	 * @return Updated event list
	 */
	@DeleteMapping("/api/admin/event/{id}")
	public Page<Event> deleteEvent(@PathVariable int id) {

		return eventService.deleteEvent(id);
	}

	/**
	 * edit a event
	 * Authorization header needed(JWT token)
	 *
	 * @param event - object(JSON) of Event class
	 * @return Updated event list
	 */
	@PutMapping("/api/admin/event")
	public Page<Event> editEvent(@RequestBody Event event) {
		Event check = null;

		check = eventService.findById(event.getId());

		if (check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not exists");
		}

		return eventService.editEvent(event);
	}

	/**
	 * add new event
	 * Authorization header needed(JWT token)
	 *
	 * @param event - object(JSON) of Event class
	 * @return Updated event list
	 */
	@PostMapping("/api/admin/event")
	public Page<Event> addEvent(@RequestBody Event event) {

		return eventService.addEvent(event);
	}

	/**
	 * list all events
	 * Return data can be sorted with the parameter value
	 * Authorization header needed(JWT token)
	 *
	 * @param date - event date
	 * @param name - event name
	 * @param sort - sorting option(attribute_name:asc/desc)
	 * @param page - number of page
	 * @param pageSize - size of page
	 * @return Updated event list
	 */
	@GetMapping("/api/admin/event")
	public Page<Event> getAll(
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "sort", required = false) String sort,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "pageSize", required = false) String pageSize
	) {

		Page<Event> event = eventService.getAllEvent(date, name, sort, page, pageSize);
		return event;

	}

	/**
	 * get a detail information of the specified event
	 * Authorization header needed(JWT token)
	 *
	 * @param id - integer value(id of the event)
	 * @return the detail information of the event
	 */
	@GetMapping("/api/admin/event/{id}")
	public ResponseEntity<EventDto> getEventDetail(@PathVariable int id) {
		try {
			EventDto eventDetail = eventService.getEventById(id);
			return new ResponseEntity<>(eventDetail, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * add new discount item
	 * Authorization header needed(JWT token)
	 *
	 * @param  id - integer value(id of the event)
	 * @param  discount - object(JSON) of Discount class
	 * @return Updated event detail
	 */
	@PostMapping("/api/admin/event/{id}")
	public EventDto addDiscount(
			@PathVariable int id,
			@RequestBody Discount discount
	) {
		return eventService.addDiscount(id, discount);
	}

	/**
	 * edit discount info
	 * Authorization header needed(JWT token)
	 *
	 * @param  id - integer value(id of the event)
	 * @param  discount - object(JSON) of Discount class
	 * @return Updated event detail
	 */
	@PutMapping("/api/admin/event/{id}")
	public EventDto editDiscount(
			@PathVariable int id,
			@RequestBody Discount discount
	) {
		return eventService.editDiscount(id, discount);
	}

	/**
	 * delete discount info
	 * Authorization header needed(JWT token)
	 *
	 * @param  eventId - String value(id of the event)
	 * @param  id - String value(id of the discount)
	 * @return Updated event detail
	 */
	@DeleteMapping("/api/admin/event/discount")
	public EventDto deleteDiscount(
			@RequestParam(name = "eventid") String eventId,
			@RequestParam(name = "discountid") String id
	) {

		return eventService.deleteDiscount(eventId, id);
	}

}

