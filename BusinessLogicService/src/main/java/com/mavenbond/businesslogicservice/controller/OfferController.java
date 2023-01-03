package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Event;
import com.mavenbond.businesslogicservice.model.Offer;
import com.mavenbond.businesslogicservice.pojo.OfferRequest;
import com.mavenbond.businesslogicservice.repository.OfferRepository;
import com.mavenbond.businesslogicservice.service.EventService;
import com.mavenbond.businesslogicservice.service.OfferService;
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
    private OfferServiceImpl offerService;
    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public ResponseEntity<List<Offer>> findAllOffers() {
        return new ResponseEntity<>(offerService.findAllOffers(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Offer> createOffer(@RequestBody OfferRequest offerRequest) {
        Optional<Event> event = eventService.findEventById(offerRequest.event_id);
        Offer offer = new Offer(offerRequest.money, offerRequest.duration, offerRequest.status, offerRequest.influencerId, offerRequest.message, event.get());

        offerService.saveOffer(offer);
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id,
                                             @RequestBody Offer offer) {
        Optional<Offer> offerEdit = offerService.findOfferById(id);

        return offerEdit.map(o -> {
            o.setMessage(o.getMessage());
            o.setMoney(o.getMoney());
            o.setDuration(o.getDuration());

            offerService.saveOffer(o);
            return new ResponseEntity<>(o, HttpStatus.OK);
        }).orElseGet(() -> {
            offerService.saveOffer(offer);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
