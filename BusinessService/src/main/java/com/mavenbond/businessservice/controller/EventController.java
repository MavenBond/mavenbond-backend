package com.mavenbond.businessservice.controller;

import com.google.common.base.Joiner;
import com.mavenbond.businessservice.domain.SpecificationsBuilder;
import com.mavenbond.businessservice.dto.SearchOperation;
import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.service.EventService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@NoArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping("/")
    public ResponseEntity<Page<Event>> findAllEvents(@RequestParam(name="pageNo" ,defaultValue = "0") Integer pageNo,
                                                     @RequestParam(name="pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(name="search", defaultValue = "", required=false) String search,
                                                     @RequestParam(name="sortBy", defaultValue = "id", required=false) String sortBy,
                                                     @RequestParam(name="isAsc", defaultValue = "false", required=false) Boolean isAsc) {
        Pageable pageable = isAsc ? PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending()) :
                                    PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        SpecificationsBuilder builder = new SpecificationsBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3).toLowerCase(), matcher.group(5));
        }
        Specification<Event> spec = builder.buildEvent();

        return new ResponseEntity<>(service.findAll(spec, pageable), HttpStatus.OK);
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
    public ResponseEntity<Page<Event>> findEventsByBusiness(@PathVariable String id,
                                                        @RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "10") Integer pageSize) {

        return new ResponseEntity<>(service.findAllByBusinessId(id, PageRequest.of(pageNo, pageSize)) ,HttpStatus.OK);
    }

//    @GetMapping("/user/{id}/platform/{platform")
//    public ResponseEntity<Page<Event>> findEventsByBusinessAndPlatform(@PathVariable String id,
//                                                            @PathVariable String platform,
//                                                            @RequestParam(defaultValue = "0") Integer pageNo,
//                                                            @RequestParam(defaultValue = "10") Integer pageSize) {
//
//        return new ResponseEntity<>(service.findAllByBusinessIdAndPlatform(id, platform, PageRequest.of(pageNo, pageSize)) ,HttpStatus.OK);
//    }
//
//    @GetMapping("/user/{id}/type/{type}")
//    public ResponseEntity<Page<Event>> findEventsByBusinessAndType(@PathVariable String id,
//                                                                   @PathVariable String type,
//                                                                   @RequestParam(defaultValue = "0") Integer pageNo,
//                                                                   @RequestParam(defaultValue = "10") Integer pageSize) {
//        return new ResponseEntity<>(service.findAllByBusinessIdAndType(id, type, PageRequest.of(pageNo, pageSize)) ,HttpStatus.OK);
//    }
}


