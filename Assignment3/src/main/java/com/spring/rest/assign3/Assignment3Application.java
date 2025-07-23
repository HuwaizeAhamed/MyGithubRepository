package com.spring.rest.assign3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.rest.assign3.service.assign3_service;

@SpringBootApplication
public class Assignment3Application  implements CommandLineRunner{
	
	@Autowired
	assign3_service aservice;

	public static void main(String[] args) {
		
		SpringApplication.run(Assignment3Application.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		aservice.initializeDatabase(); // Create the table on startup
    }
}
