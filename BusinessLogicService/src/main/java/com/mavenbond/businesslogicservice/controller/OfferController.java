package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Offer;
import com.mavenbond.businesslogicservice.service.OfferService;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/offers")
@CrossOrigin
public class OfferController {
    private OfferService offerService;

    @GetMapping("/")
    public ResponseEntity<List<Offer>> findAllOffers() {
        return new ResponseEntity<>(offerService.findAllOffers(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
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
