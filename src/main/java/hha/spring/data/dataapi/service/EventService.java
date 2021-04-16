package hha.spring.data.dataapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;

import hha.spring.data.dataapi.domain.event.Discount;
import hha.spring.data.dataapi.domain.event.EventDto;
import hha.spring.data.dataapi.repository.event.DiscountRepository;
import hha.spring.data.dataapi.repository.event.EventItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.event.Event;
import hha.spring.data.dataapi.repository.event.EventRepository;

/**
 * This class is a business logic to manage event data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventItemRepository eventItemRepository;

	@Autowired
	private DiscountRepository discountRepository;

	/**
	 * Delete event page.
	 *
	 * @param id the id
	 * @return the page
	 */
	public Page<Event> deleteEvent(int id) {

		eventRepository.deleteById(id);

		return getAllEvent(null, null,null, null, null);
	}

	/**
	 * Find by id event.
	 *
	 * @param id the id
	 * @return the event
	 */
	public Event findById(int id) {

		return eventRepository.findById(id).get();
	}

	/**
	 * Edit event page.
	 *
	 * @param event the event
	 * @return the page
	 */
	public Page<Event> editEvent(Event event) {
		try {
			eventRepository.save(event);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return getAllEvent(null, null,null, null, null);
	}

	/**
	 * Add event page.
	 *
	 * @param event the event
	 * @return the page
	 */
	public Page<Event> addEvent(Event event) {
		try {
			eventRepository.save(new Event(event.getTitle(),event.getStartDate(),event.getEndDate(),event.getDescription()));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		return getAllEvent(null, null,null, null, null);
	}

	/**
	 * Get all event page.
	 *
	 * @param date     the date
	 * @param nm       the nm
	 * @param sort     the sort
	 * @param page     the page
	 * @param pageSize the page size
	 * @return the page
	 */
	public Page<Event> getAllEvent(String date, String nm, String sort, String page, String pageSize){

		String sortProp = "event_id";
		String order = "desc";
		int pageNumber = 1;
		int pageS=10;

		if ( page != null ) pageNumber = Integer.parseInt(page);
		if( pageSize != null) pageS = Integer.parseInt(pageSize);

		if ( sort != null) {
			sortProp= sort.split(":")[0];
			order = sort.split(":")[1];
		}

		Pageable pageable = PageRequest.of(pageNumber - 1, pageS, Sort.Direction.fromString(order), sortProp);

		String name = "";

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Date minEventDate = null;
		Date maxEventDate = null;

		try {
			minEventDate = sdf.parse("2021-01-01");
			maxEventDate = sdf.parse("3021-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(date != null) {
			String[] dates = date.split(":");

			try {
				minEventDate = sdf.parse(dates[0]);
				maxEventDate = sdf.parse(dates[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if(nm != null) {
			name = nm;
		}

		return eventRepository.getEvent(
				name,
				minEventDate, maxEventDate, pageable
		);

	}

	/**
	 * Gets event by id.
	 *
	 * @param id the id
	 * @return the event by id
	 */
	public EventDto getEventById(int id) {

		Event ev = eventRepository.findById(id).get();

		return new EventDto(id, ev.getTitle(), ev.getStartDate(), ev.getEndDate(), ev.getDescription(), eventItemRepository.listAllItem(id));
	}

	/**
	 * Add discount event dto.
	 *
	 * @param id       the id
	 * @param discount the discount
	 * @return the event dto
	 */
	public EventDto addDiscount(int id, Discount discount) {

		Discount newDiscount = new Discount(discount.getProductId(), id, discount.getDiscountPrice(), 99);

		discountRepository.save(newDiscount);

		Event ev = eventRepository.findById(id).get();

		return new EventDto(id, ev.getTitle(), ev.getStartDate(), ev.getEndDate(), ev.getDescription(), eventItemRepository.listAllItem(id));
	}

	/**
	 * Edit discount event dto.
	 *
	 * @param id       the id
	 * @param discount the discount
	 * @return the event dto
	 */
	public EventDto editDiscount(int id, Discount discount) {

		discountRepository.findById(discount.getId());
		discountRepository.save(discount);

		Event ev = eventRepository.findById(id).get();

		return new EventDto(id, ev.getTitle(), ev.getStartDate(), ev.getEndDate(), ev.getDescription(), eventItemRepository.listAllItem(id));
	}

	/**
	 * Delete discount event dto.
	 *
	 * @param eventId    the event id
	 * @param discountId the discount id
	 * @return the event dto
	 */
	public EventDto deleteDiscount(String eventId, String discountId) {

		discountRepository.deleteById(Integer.parseInt(discountId));

		Event ev = eventRepository.findById(Integer.parseInt(eventId)).get();

		return new EventDto(Integer.parseInt(eventId), ev.getTitle(), ev.getStartDate(), ev.getEndDate(), ev.getDescription(), eventItemRepository.listAllItem(Integer.parseInt(eventId)));
	}

}
