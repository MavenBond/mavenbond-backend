package com.mavenbond.userservice.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.model.Customer;

import com.mavenbond.userservice.model.Influencer;
import com.mavenbond.userservice.service.BusinessService;
import com.mavenbond.userservice.service.InfluencerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private BusinessService businessService;
    @Autowired
    private InfluencerService influencerService;

    @KafkaListener(topics = "SAVE_BUSINESS", groupId = "group_id")
    public void saveBusiness(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();

        Business business = gson.fromJson(json, Business.class);
        logger.info(String.format("#### -> Consumed message -> %s", business));

        // save item into database
        businessService.save(business);
        logger.info("#### -> Finished saving Item");
    }

    @KafkaListener(topics = "SAVE_INFLUENCER", groupId = "group_id")
    public void saveInfluencer(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();

        Influencer influencer = gson.fromJson(json, Influencer.class);
        logger.info(String.format("#### -> Consumed message -> %s", influencer));

        // save item into database
        influencerService.save(influencer);
        logger.info("#### -> Finished saving Item");
    }
}
