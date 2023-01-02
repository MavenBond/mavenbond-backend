package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.service.EventService;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin
@NoArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping("/")
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<>(eventService.findAllEvents(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        eventService.saveEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,
                                             @RequestBody Event event) {
        Optional<Event> eventEdit = eventService.findEventById(id);

        return eventEdit.map(e -> {
            e.setTitle(event.getTitle());
            e.setSubject(event.getSubject());
            e.setDescription(event.getDescription());
            e.setType(event.getType());
            e.setPlatform(event.getPlatform());
            e.setStartDate(event.getStartDate());
            e.setEndDate(event.getEndDate());
            e.setMoneyMin(event.getMoneyMin());
            e.setMoneyMax(event.getMoneyMax());

            eventService.saveEvent(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> {
            eventService.saveEvent(event);
            return new ResponseEntity<>(event, HttpStatus.OK);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

//    private EventRepository eventRepository;
//
//    @GetMapping("/events")
//    public List<Event> findAllEvents() {
//        return eventRepository.findAll();
//    }
//
//    @PostMapping("/questions")
//    public Event createEvent(@Valid @RequestBody Event event) {
//        return eventRepository.save(event);
//    }
//
//    @PutMapping("/questions/{eventId}")
//    public Event updateEvent(@PathVariable Long eventId,
//                                   @Valid @RequestBody Event eventRequest) {
//        return eventRepository.findById(eventId)
//        .map(event -> {
//            question.setTitle(questionRequest.getTitle());
//
//        })
//    }


