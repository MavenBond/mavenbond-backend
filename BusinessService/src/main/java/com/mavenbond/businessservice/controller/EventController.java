package com.mavenbond.businessservice.controller;

import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.service.EventServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@NoArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    @Autowired
    private EventServiceImpl service;

    @GetMapping("/")
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        Optional<Event> eventOptional = service.findById(id);

        return eventOptional
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> (new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        service.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,
                                             @RequestBody Event event) {
        Optional<Event> eventOptional = service.findById(id);

        return eventOptional.map(e -> {
            event.setId(e.getId());
            service.save(event);
            return new ResponseEntity<>(event, HttpStatus.OK);
        }).orElseGet(() -> (
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Event>> findEventsByUser(@PathVariable String id) {
        Optional<List<Event>> eventListOptional = service.findByUserId(id);

        return eventListOptional
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> (new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }
}


