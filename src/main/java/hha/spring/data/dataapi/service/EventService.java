package hha.spring.data.dataapi.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.Event;
import hha.spring.data.dataapi.repository.EventRepository;

@Service
@Transactional
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	

	public void deleteEvent(int id) {
		eventRepository.deleteById(id);
	}


	public Object findById(int id) {
		return eventRepository.findById(id);
	}


	public String editEvent(Event event) {
		try {
			eventRepository.save(event);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return "Successfully edited";
	}


	public String addEventBulk(List<Event> eventList) {
		for(Event event:eventList) {
			eventRepository.save(event);
		}
		return "Successfully added";
	}


	public Event getEventById(int id) {
		return eventRepository.findById(id).get();
	}
	
  

}
