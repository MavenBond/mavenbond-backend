package com.mavenbond.businessservice.controller;

import com.mavenbond.businessservice.dto.OfferDto;
import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.pojo.OfferRequest;
import com.mavenbond.businessservice.service.EventService;
import com.mavenbond.businessservice.service.OfferService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/offers")
public class OfferController {
    @Autowired
    private OfferService service;
    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public ResponseEntity<List<OfferDto>> findAllOffers() {
        return new ResponseEntity<>(service.findAll().stream().map(OfferDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable Long id) {
        Optional<Offer> offerOptional = service.findById(id);

        return offerOptional
                .map(o -> new ResponseEntity<>(new OfferDto(o), HttpStatus.OK))
                .orElseGet(() -> (new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("/")
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferRequest offerRequest) {
        Optional<Event> eventOptional = eventService.findById(offerRequest.event_id);

        return eventOptional.map(e -> {
            Offer offer = new Offer(offerRequest.money, offerRequest.duration, offerRequest.status, offerRequest.influencerId, offerRequest.message, e);
            service.save(offer);
            return new ResponseEntity<>(new OfferDto(offer), HttpStatus.CREATED);
        }).orElseGet(() -> (
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable Long id,
                                             @RequestBody Offer offer) {
        Optional<Offer> offerOptional = service.findById(id);

        return offerOptional.map(o -> {
            offer.setId(o.getId());
            service.save(offer);
            return new ResponseEntity<>(new OfferDto(offer), HttpStatus.OK);
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
