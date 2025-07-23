package com.spring.rest.assign3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.assign3.model.assign3_payloadModel;
import com.spring.rest.assign3.service.assign3_service;

@RestController
public class assign3_Controller {

	@Autowired
	assign3_service aservice;

	@PostMapping(value = "/jsonInsertion/{msg}", consumes = "application/json", produces = "application/xml")
	public ResponseEntity<String> jsonInsertController(@RequestBody assign3_payloadModel model,
			@PathVariable("msg") String pathMsg, @RequestParam(required = true) String queryKey,
			@RequestHeader(required = true) String headerKey) {

		String serviceResponse = aservice.jsonInsertService(model, pathMsg, queryKey, headerKey);

		if (serviceResponse.contains("DB persistence failure")) {
			return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	}

}
