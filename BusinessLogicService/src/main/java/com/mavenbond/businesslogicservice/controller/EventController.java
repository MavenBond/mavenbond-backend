package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.service.EventServiceImpl;
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
    private EventServiceImpl eventService;

    @GetMapping("/")
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        eventService.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,
                                             @RequestBody Event event) {
        Optional<Event> eventOptional = eventService.findById(id);

        return eventOptional.map(e -> {
            e.setTitle(event.getTitle());
            e.setSubject(event.getSubject());
            e.setDescription(event.getDescription());
            e.setType(event.getType());
            e.setPlatform(event.getPlatform());
            e.setStartDate(event.getStartDate());
            e.setEndDate(event.getEndDate());
            e.setMoneyMin(event.getMoneyMin());
            e.setMoneyMax(event.getMoneyMax());

            eventService.save(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> {
//            eventService.saveEvent(event);
            return new ResponseEntity<>(event, HttpStatus.NO_CONTENT);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


