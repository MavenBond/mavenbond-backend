package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository repository;

    @Override
    public List<Event> findAll() {return repository.findAll();}

    @Override
    public void save(Event event) { repository.save(event); }

    @Override
    public void delete(Long eventId) { repository.deleteById(eventId); }

    @Override
    public Optional<Event> findById(Long id) { return repository.findById(id); }
}
