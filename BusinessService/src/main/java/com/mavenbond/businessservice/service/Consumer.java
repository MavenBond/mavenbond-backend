package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.repository.EventRepository;
import com.mavenbond.businessservice.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OfferRepository offerRepository;

    @KafkaListener(topics = "events", groupId = "group_id")
    public void consumeEvent(Event event) throws IOException {
        logger.info(String.format("#### -> Consumed event -> %s", event.getTitle()));

        // save event to database
        eventRepository.save(event);
    }

    @KafkaListener(topics = "offers", groupId = "group_id")
    public void consumeOffer(Offer offer) throws IOException {
        logger.info(String.format("#### -> Consumed offer for event -> %s", offer.getEvent().getTitle()));

        // save event to database
        offerRepository.save(offer);
    }
}
