package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.model.Offer;
import com.mavenbond.businesslogicservice.pojo.OfferRequest;
import com.mavenbond.businesslogicservice.service.EventService;
import com.mavenbond.businesslogicservice.service.OfferServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/offers")
public class OfferController {
    @Autowired
    private OfferServiceImpl service;
    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public ResponseEntity<List<Offer>> findAllOffers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> findOfferById(@PathVariable Long id) {
        Optional<Offer> offerOptional = service.findById(id);

        return offerOptional
                .map(o -> new ResponseEntity<>(o, HttpStatus.OK))
                .orElseGet(() -> (new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("/")
    public ResponseEntity<Offer> createOffer(@RequestBody OfferRequest offerRequest) {
        Optional<Event> eventOptional = eventService.findById(offerRequest.event_id);

        return eventOptional.map(e -> {
            Offer offer = new Offer(offerRequest.money, offerRequest.duration, offerRequest.status, offerRequest.influencerId, offerRequest.message, e);
            service.save(offer);
            return new ResponseEntity<>(offer, HttpStatus.CREATED);
        }).orElseGet(() -> (
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id,
                                             @RequestBody Offer offer) {
        Optional<Offer> offerOptional = service.findById(id);

        return offerOptional.map(o -> {
            offer.setId(o.getId());
            service.save(offer);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        }).orElseGet(() -> (
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
