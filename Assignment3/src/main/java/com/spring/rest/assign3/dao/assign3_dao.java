package com.spring.rest.assign3.dao;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.assign3.model.assign3_payloadModel;

@Repository
public class assign3_dao {

	public static final Logger log = LogManager.getLogger(assign3_dao.class);

	@Autowired
	JdbcTemplate jt;
	@Autowired
	ObjectMapper om ;
	

//	assign3_payloadModel model;

	public String insertJson(assign3_payloadModel model, String pathMsg, String queryKey, String headerKey) {

		String sql1 = "insert into JDBC_test(Emp_Name,empid,company,phoneNumber,department,address) values(?,?,?,?,?,?)";
//		String sql2 = "insert into JDBC_add(doorNo,Street,area,city) values(?,?,?,?)";

		log.info(" " + model.getName() + " " + model.getEmpid() + " " + pathMsg + " " + queryKey + " " + headerKey + " "
				+ model.getAddress());

		try {
			
			String addressres = om.writeValueAsString(model.getAddress());
			log.info(addressres);

			int rows = jt.update(sql1, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, model.getName());
					
					ps.setString(2, model.getEmpid()); 
					ps.setString(3, pathMsg); 
					ps.setString(4, queryKey); 
					ps.setString(5, headerKey); 
					if (addressres == null) {
						throw new IllegalArgumentException("Address JSON cannot be null");
					}
					ps.setCharacterStream(6, new StringReader(addressres), addressres.length());
				}
			});

			log.info("Rows Inserted");
			log.info(rows + " - row/s affected");
			return "DB insertion success";
		} catch (Exception e) {
			log.error(e.getMessage());
			String sql = "DROP JDBC_TEST";
			log.info("TABLE JDBC_TEST has been dropped successfully");
			jt.execute(sql);
			return "DB persistence failure";

		}

	}

	public void createTable() {
		String sql = "CREATE TABLE JDBC_TEST(Emp_Name VARCHAR(255),empid VARCHAR(255) PRIMARY KEY,company VARCHAR(255),phoneNumber VARCHAR(10),department VARCHAR(255),address CLOB)";

		try {
			jt.execute(sql);
			log.info("Table JDBC_test created successfully.");
		} catch (Exception e) {
			log.error("Error creating table: {}", e.getMessage());
			throw e; // Rethrow or handle as needed
		}

	}

	public void dropTable() {
		String sql = "DROP JDBC_TEST";
		log.info("TABLE JDBC_TEST has been dropped successfully");
		jt.execute(sql);

	}
}
