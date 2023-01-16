package com.mavenbond.businessservice.controller;

import com.google.common.base.Joiner;
import com.mavenbond.businessservice.domain.SpecificationsBuilder;
import com.mavenbond.businessservice.dto.SearchOperation;
import com.mavenbond.businessservice.dto.OfferDto;
import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.pojo.OfferRequest;
import com.mavenbond.businessservice.service.EventService;
import com.mavenbond.businessservice.service.OfferService;
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
@NoArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/offers")
public class OfferController {
    @Autowired
    private OfferService service;
    @Autowired
    private EventService eventService;

//    @GetMapping("/")
//    public ResponseEntity<List<Offer>> findAllOffers() {
//        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/")
    public ResponseEntity<Page<OfferDto>> findAllOffers(@RequestParam(name="pageNo" ,defaultValue = "0") Integer pageNo,
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
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<Offer> spec = builder.buildOffer();

        return new ResponseEntity<>(service.findAll(spec, pageable).map(OfferDto::new), HttpStatus.OK);
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
