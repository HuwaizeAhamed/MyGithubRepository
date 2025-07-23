package com.spring.crud.assign5.hibernate.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crud.assign5.hibernate.daorepo.HibernateDao;
import com.spring.crud.assign5.hibernate.model.HibernateModel;


@Service
public class HibernateService {

	@Autowired
	HibernateDao hDao;

	private static final Log log = LogFactory.getLog(HibernateService.class);

	public String insertData(HibernateModel req) {

		try {

			String daoResponse = hDao.insertDataDB(req);
			log.info("DaoResponse => " + daoResponse);
			return daoResponse;

		} catch (Exception e) {
			e.getLocalizedMessage();
			log.info("Error in service");
			return "Error while inserting into DB " + e.getLocalizedMessage();
		}

	}

	public HibernateModel getData(int id) {
		HibernateModel hm1;
		try {
			
			hm1=hDao.getDataDB(id);
			log.info("DaoResponse => " + hm1);
			return hm1;
			
		}catch(Exception e) {
			return null;
		}
		
		
	}

	public HibernateModel updateData(HibernateModel req) {
		HibernateModel hm1;
		try {
			
			hm1=hDao.updateDataDB(req);
			log.info("DaoResponse => " + hm1);
			return hm1;
			
		}catch(Exception e) {
			return null;
		}
	}

	public StringBuffer removeData(HibernateModel req) {
		StringBuffer daoResponse =null;
		try {
			
			 daoResponse = hDao.deleteDataDB(req);
			log.info("DaoResponse => " + daoResponse);
			return daoResponse;
			
		}catch(Exception e) {
			e.getLocalizedMessage();
			log.info("Error in service");
			return daoResponse;
		}
	}

	public List<HibernateModel> getAllData() {
		List<HibernateModel> hm1 = null;
		try {
			
			hm1=hDao.getAllDataDB();
			log.info("DaoResponse => " + hm1);
			return hm1;
			
		}catch(Exception e) {
			return hm1;
		}
	}

}
