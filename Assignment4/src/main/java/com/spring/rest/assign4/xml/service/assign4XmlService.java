package com.spring.rest.assign4.xml.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.assign4.xml.model.xmlPayloadModel;
import com.spring.rest.assign4.xml.repository.assign4XmlDao;

@Service
public class assign4XmlService {
	
	private static final Logger log=LogManager.getLogger(assign4XmlService.class);

	
	@Autowired
	assign4XmlDao dao;

	public String xmlInsertService(xmlPayloadModel model, String phone, String company) {
		
		log.info("Inside Service Execution Started");
		String serviceResponse = null;
		
		
		
		
		serviceResponse=dao.xmlInsert(model,phone,company);
		if(serviceResponse.contains("Xml Data Persistence Success")) {
			
			log.info("Inside Service Execution Completed");
			return serviceResponse;
		}
		else {
			log.info("Error Service Execution Completed");
			return "Error in inserting XML Data"+serviceResponse;
		}
		
	}

	
}
