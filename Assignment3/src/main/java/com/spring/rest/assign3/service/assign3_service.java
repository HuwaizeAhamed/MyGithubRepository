package com.spring.rest.assign3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.assign3.dao.assign3_dao;
import com.spring.rest.assign3.model.assign3_payloadModel;

@Service
public class assign3_service {

	@Autowired
	assign3_dao adao;

	public String jsonInsertService(assign3_payloadModel model, String pathMsg, String queryKey, String headerKey) {
		String dbResponse = null;
		try {
			dbResponse = adao.insertJson(model, pathMsg, queryKey, headerKey);

			return dbResponse;
		} catch (Exception e) {
			dbResponse = "Error in insertion " + e.getMessage();
//			adao.dropTable();
			return dbResponse;

		}

	}
	
	public void initializeDatabase() {
        adao.createTable(); // Create the table if it doesn't exist
    }

}
