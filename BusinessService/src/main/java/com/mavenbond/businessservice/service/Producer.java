package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.model.Offer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Event> kafkaEventTemplate;
    @Autowired
    private KafkaTemplate<String, Offer> kafkaOfferTemplate;

    public void sendEvent(Event event) {
        logger.info(String.format("#### -> Producing event -> %s", event.getTitle()));
        this.kafkaEventTemplate.send("events", event);
    }

    public void sendOffer(Offer offer) {
        logger.info(String.format("#### -> Producing offer for event -> %s", offer.getEvent().getId()));
        this.kafkaOfferTemplate.send("offers", offer);
    }
}

