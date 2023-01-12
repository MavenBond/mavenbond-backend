package com.mavenbond.businessservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
//    @Bean
//    public NewTopic eventTopic() {
//        return TopicBuilder.name("events")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public NewTopic offerTopic() {
//        return TopicBuilder.name("offers")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
}
