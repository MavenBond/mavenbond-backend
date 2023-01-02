package com.mavenbond.businesslogicservice.service;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.repository.EventRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository repository;

    @Override
    public List<Event> findAllEvents() {return repository.findAll();}

    @Override
    public void saveEvent(Event event) { repository.save(event); }

    @Override
    public void deleteEvent(Long eventId) { repository.deleteById(eventId); }

    @Override
    public Optional<Event> findEventById(Long eventId) { return repository.findById(eventId); }
}
