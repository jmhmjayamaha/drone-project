package com.musalasoft.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Drone project APIs", version = "1.0", description = "This project is created for testing purpose"))
public class MusalasoftDroneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusalasoftDroneApplication.class, args);
	}

}
