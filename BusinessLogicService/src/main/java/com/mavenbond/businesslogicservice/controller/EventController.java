package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.repository.EventRepository;
import com.mavenbond.businesslogicservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> findAllEvents(Model model) {
        var events = (List<Event>) eventService.findAllEvents();
        model.addAttribute("events", events);
        return events;
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


