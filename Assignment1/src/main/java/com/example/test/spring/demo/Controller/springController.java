package com.example.test.spring.demo.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.spring.demo.Model.xmlRoot;

@RestController
public class springController {
	
//	Logger log1=LoggerFactory.getLogger(springController.class);
	private static Logger log=LogManager.getLogger(springController.class);

	@GetMapping(value = "/restApiParsingJson/{msg}", produces = "application/json")
	public ResponseEntity<xmlRoot> jsonParsing(@PathVariable String msg) {
		
		log.info("Json Method initiated");

		xmlRoot message = new xmlRoot(msg, "Api is working");
		
		log.info("Json Method Completed");

		return new ResponseEntity<>(message, HttpStatus.OK);
		

	}

	@GetMapping(value = "/restApiParsingXml/{msg}", produces = "application/xml")
	public ResponseEntity<xmlRoot> xmlParsing(@PathVariable String msg) {
		
		log.info("Xml Method initiated");

		String desc = "Api is working";

		xmlRoot message = new xmlRoot(msg, desc);
		
		log.info("Xml Method Completed");

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
