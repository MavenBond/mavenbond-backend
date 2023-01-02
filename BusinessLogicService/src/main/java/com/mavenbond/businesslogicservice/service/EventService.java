package com.mavenbond.businesslogicservice.service;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.repository.EventRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class EventService {
    private EventRepository repository;

    public List<Event> findAllEvents() {return repository.findAll();}
    public void saveEvent(Event event) { repository.save(event); }
    public void deleteEvent(Long eventId) { repository.deleteById(eventId); }
    public Optional<Event> findEventById(Long eventId) { return repository.findById(eventId); }
}
