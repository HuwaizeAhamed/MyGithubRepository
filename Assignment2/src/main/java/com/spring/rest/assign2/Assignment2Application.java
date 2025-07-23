package com.spring.rest.assign2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.rest.assign2.controller.assignment2ApiController;

@SpringBootApplication
public class Assignment2Application {
	
	private static final Logger log = LogManager.getLogger(assignment2ApiController.class);
	

	public static void main(String[] args) {
		
		log.info("Starting the Application ");
		SpringApplication.run(Assignment2Application.class, args);
		

	}

}
