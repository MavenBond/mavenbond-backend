package com.mavenbond.businesslogicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BusinessLogicServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusinessLogicServiceApplication.class, args);
	}

}
