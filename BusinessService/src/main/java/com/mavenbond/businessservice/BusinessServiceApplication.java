package com.mavenbond.businessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
public class BusinessServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusinessServiceApplication.class, args);
	}

}
