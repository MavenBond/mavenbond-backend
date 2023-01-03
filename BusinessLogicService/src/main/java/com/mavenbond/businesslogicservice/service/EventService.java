package com.mavenbond.businesslogicservice.service;

import com.mavenbond.businesslogicservice.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    void save(Event event);
    void delete(Long eventId);
    Optional<Event> findById(Long id);
}
