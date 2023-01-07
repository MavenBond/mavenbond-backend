package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    void save(Event event);
    void delete(Long eventId);
    Optional<Event> findById(Long id);
    Optional<List<Event>> findByUserId(String id);
}
