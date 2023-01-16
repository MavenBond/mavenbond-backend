package com.mavenbond.businessservice.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public <T> void sendSaveRequest(T t, String topic) {
        String json = parseJson(t);
        this.kafkaTemplate.send(topic, json);
    }

    public <T> String parseJson(T t) {
        logger.info(String.format("#### -> Producing message -> %s", t));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        return gson.toJson(t);
    }
}
