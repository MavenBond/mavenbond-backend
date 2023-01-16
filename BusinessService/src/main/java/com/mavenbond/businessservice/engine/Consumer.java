package com.mavenbond.businessservice.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.pojo.OfferRequest;
import com.mavenbond.businessservice.service.EventService;
import com.mavenbond.businessservice.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private OfferService offerService;

    @KafkaListener(topics = "SAVE_EVENT", groupId = "group_id")
    public void saveEvent(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();

        Event event = gson.fromJson(json, Event.class);
        logger.info(String.format("#### -> Consumed message -> %s", event));

        // save item into database
        eventService.save(event);
        logger.info("#### -> Finished saving Item");
    }

    @KafkaListener(topics = "SAVE_OFFER", groupId = "group_id")
    public void saveOffer(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();

        Offer offer = gson.fromJson(json, Offer.class);
        logger.info(String.format("#### -> Consumed message -> %s", offer));

        // save item into database
        offerService.save(offer);
        logger.info("#### -> Finished saving Item");
    }
}
