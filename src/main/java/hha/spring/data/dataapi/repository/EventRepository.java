package hha.spring.data.dataapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hha.spring.data.dataapi.domain.Event;


public interface EventRepository extends JpaRepository<Event, Integer> {

}
