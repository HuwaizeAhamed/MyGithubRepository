package com.spring.rest.assign2.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.spring.rest.assign2.model.assignment2RequestModel;
//import com.spring.rest.assign2.model.assignment2XmlModel;


@RestController
@RequestMapping("/rest")
@Component
public class assignment2ApiController {

	private static final Logger log = LogManager.getLogger(assignment2ApiController.class);

	@PostMapping(value = "/a2JsonToXml", produces = "application/xml", consumes = "application/json")
	
	public ResponseEntity<String> jsonToXml(@RequestBody assignment2RequestModel requestMessage) {
		log.info("Post Method Initiated");
		log.info("JSON Controller class Initiated");
		String body = null;
		HttpHeaders headers=new HttpHeaders();
		try {
			XmlMapper xm = new XmlMapper();

			body = xm.writeValueAsString(requestMessage);
			
			
			headers.add("Accept", "*/*");
			headers.add("Content-Type", "application/xml");

		} catch (Exception e) {
			log.error("Caught Exception in Controller");

		}

		log.info("JSON Controller class Completed");

		log.info("Post Method Completed");
		
		log.info("Application Execution Completed");

		return new ResponseEntity<>(body, headers,HttpStatus.OK);
	}

	@PostMapping(value = "/a2xmlToJson", produces = "application/json", consumes = "application/xml")

	public ResponseEntity<String> xmlToJson(@RequestBody assignment2RequestModel requestMessage) {
		log.info("Post Method Initiated");
		log.info("XML Controller class Initiated");
		String body = null;

		try {

			ObjectMapper jom = new ObjectMapper();

			body = jom.writeValueAsString(requestMessage);

		} catch (Exception e) {
			log.error("Caught Exception in Controller");

		}

		log.info("XML Controller class Completed");

		log.info("Post Method Completed");
		
		log.info("Application Execution Completed");

		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	
	@Autowired
	private Environment environment;
	
	
	@GetMapping("/profiles1")
	
	public String[] readProfiles1() {
		
		for(String profiles:environment.getActiveProfiles()) {
			log.info("for Each Active Profiles == "+profiles);
			
		}		
		return environment.getActiveProfiles();
	}
	
	@Value("${spring.profiles.active}")
	private String[] profile;
	@GetMapping("/profiles2")
	public String[] readProfiles2() {
		
		
		for(String profiles:environment.getActiveProfiles()) {
			log.info("for Each Active Profiles == "+profiles);
			
			
		}
		
		return profile;
	}
	
	@GetMapping("/profiles3")
	public String[] readProfiles3() {
		
		
		
		for(String profiles:environment.getDefaultProfiles()) {
			log.info("for Each Active Profiles == "+profiles);
			
		}
		
		return environment.getDefaultProfiles();
	}

}
