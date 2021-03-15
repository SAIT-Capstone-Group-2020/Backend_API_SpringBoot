package hha.spring.data.dataapi.controller;

import java.util.List;
import java.util.NoSuchElementException;

import hha.spring.data.dataapi.domain.event.Discount;
import hha.spring.data.dataapi.domain.event.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.event.Event;
import hha.spring.data.dataapi.service.EventService;

@CrossOrigin
@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@DeleteMapping("/api/admin/event/{id}")
	public Page<Event> deleteEvent(@PathVariable int id) {

		return eventService.deleteEvent(id);
	}

	@PutMapping("/api/admin/event")
	public Page<Event> editEvent(@RequestBody Event event) {
		Event check = null;

		check = eventService.findById(event.getId());

		if (check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not exists");
		}
<<<<<<< HEAD
		 
		 String result=eventService.editEvent(event);
		 
		return result;
		
	  }
	 
//		@PostMapping("/api/admin/product/bulk")
//		@PreAuthorize("hasRole('ROLE_ADMIN')")
//		public String addProductBulk(@RequestBody List<Event> eventList) {
//
//			String result = eventService.addEventBulk(eventList);
//
//			return result;
//		}
		
		  @GetMapping("/api/Event/{id}")
		    public ResponseEntity<Event> get(@PathVariable int id) {
		        try {
		            Event event = eventService.getEventById(id);
		            return new ResponseEntity<Event>(event, HttpStatus.OK);
		        } catch (NoSuchElementException e) {
		            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		        }
		    }
		
=======

		return eventService.editEvent(event);
	}

	@PostMapping("/api/admin/event")
	public Page<Event> addEvent(@RequestBody Event event) {

		return eventService.addEvent(event);
	}

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

	@GetMapping("/api/admin/event/{id}")
	public ResponseEntity<EventDto> getEventDetail(@PathVariable int id) {
		try {
			EventDto eventDetail = eventService.getEventById(id);
			return new ResponseEntity<EventDto>(eventDetail, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<EventDto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/api/admin/event/{id}")
	public EventDto addDiscount(
			@PathVariable int id,
			@RequestBody Discount discount
	) {
		return eventService.addDiscount(id, discount);
	}

	@PutMapping("/api/admin/event/{id}")
	public EventDto editDiscount(
			@PathVariable int id,
			@RequestBody Discount discount
	) {
		return eventService.editDiscount(id, discount);
	}

	@DeleteMapping("/api/admin/event/discount")
	public EventDto deleteDiscount(
			@RequestParam(name = "eventid") String eventId,
			@RequestParam(name = "discountid") String id
	) {

		return eventService.deleteDiscount(eventId, id);
	}

>>>>>>> 01bda963616f4e6a2ae5af778129d61b8a33418e
}
