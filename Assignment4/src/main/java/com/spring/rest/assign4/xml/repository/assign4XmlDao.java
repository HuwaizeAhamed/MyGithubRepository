package com.spring.rest.assign4.xml.repository;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.spring.rest.assign4.xml.model.xmlPayloadModel;

@Repository
public class assign4XmlDao {
	
	private static final Logger log=LogManager.getLogger(assign4XmlDao.class);
	
	@Autowired
	JdbcTemplate jdbc;
	

	public String xmlInsert(xmlPayloadModel model, String phone, String company) {
		
		try{
			XmlMapper xm =new XmlMapper();
			
			final String xmlModel=xm.writeValueAsString(model.getAddress());
			
			
		String sql="Insert INTO JDBC_XML_TEST(EMPNAME,EMPID,COMPANY,PHONENUMBER,DEPARTMENT,ADDRESS) Values (?,?,?,?,?,?)";
		int rows=jdbc.update(sql,new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				
				
				ps.setString(1, model.getName());
				ps.setString(2, model.getEmpid()); 
				ps.setString(3, company); 
				ps.setString(4, phone); 
				ps.setString(5, model.getDepartment()); 
				ps.setCharacterStream(6, new StringReader(xmlModel), xmlModel.length());
			}
		});
		
		log.info("Rows Inserted");
		log.info(rows +" - row/s affected");
		return "Xml Data Persistence Success";
	}catch(Exception e) {
		
//		String sql = "DROP JDBC_XML_TEST";
		log.info("TABLE JDBC_XML_TEST has been dropped successfully"+e.getMessage());
//		jdbc.execute(sql);
		log.info("Insertion Failed");
		return "Xml Data Persistence Failure"+e.getMessage();
	}
	}

}
