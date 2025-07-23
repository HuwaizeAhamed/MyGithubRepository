package com.spring.rest.assign4.xml.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.assign4.xml.model.xmlPayloadModel;
import com.spring.rest.assign4.xml.service.assign4XmlService;

@RestController
@RequestMapping("/xml")
public class assign4XmlController {
	
	private static final Logger log=LogManager.getLogger(assign4XmlController.class);

	
	@Autowired
	assign4XmlService service;

	@PostMapping(value = "/xmlInsertion/{phone}", consumes = "application/xml",produces="application/json")
	public ResponseEntity<String> xmlInsertController(@RequestBody xmlPayloadModel model, @PathVariable(required=true) String phone,
			@RequestHeader(required=true) String company) {
		log.info("Inside Controller Execution Started");
		String serviceResponse=service.xmlInsertService(model,phone,company);
		if(serviceResponse.contains("Xml Data Persistence Success")) {
			log.info("Inside Controller Execution Completed");

		return new ResponseEntity<>(serviceResponse,HttpStatus.OK);
		}
		else {
			log.info("Error Controller Execution Completed");
			return new ResponseEntity<>(serviceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
