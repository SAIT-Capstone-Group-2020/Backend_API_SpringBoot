package hha.spring.data.dataapi.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.Event;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.domain.WeightType;
import hha.spring.data.dataapi.service.EventService;

@RestController
public class EventController {
	private EventService eventService;
	
	 @DeleteMapping("/api/admin/event/{id}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public void deleteEvent(@PathVariable int id) {
		 eventService.deleteEvent(id);
	 }


	 @PutMapping("/api/admin/event")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public String editEvent(@RequestBody Event event) {
		 Event check =null;
		 
		 check=(Event) eventService.findById(event.getId());
		 
		 if(check == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not exists");
		}
		 
		 String result=eventService.editEvent(event);
		 
		return result;
		
	  }
	 
		@PostMapping("/api/admin/product/bulk")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public String addProductBulk(@RequestBody List<Event> eventList) {

			String result = eventService.addEventBulk(eventList);

			return result;
		}
		
		  @GetMapping("/api/Event/{id}")
		    public ResponseEntity<Event> get(@PathVariable int id) {
		        try {
		            Event event = eventService.getEventById(id);
		            return new ResponseEntity<Event>(event, HttpStatus.OK);
		        } catch (NoSuchElementException e) {
		            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		        }
		    }
		
}
