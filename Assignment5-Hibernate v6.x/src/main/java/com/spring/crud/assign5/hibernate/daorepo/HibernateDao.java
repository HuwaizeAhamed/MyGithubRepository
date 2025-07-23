package com.spring.crud.assign5.hibernate.daorepo;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.spring.crud.assign5.hibernate.model.HibernateModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class HibernateDao {

	@PersistenceContext
	private EntityManager em;

	private static final Log log = LogFactory.getLog(HibernateDao.class);

	public String insertDataDB(HibernateModel req) {

		log.info("Inside Dao Layer");

		try {

			log.info("Begin Transaction");

			em.persist(req);

			log.info("Persisted Transaction");

			log.info("Data inserted into DB successfully");

		} catch (Exception e) {
			e.getLocalizedMessage();
			log.info("Error while inserting into DB ");
			log.info("RollBacking the Transaction");
			return "Error while inserting into DB " + e.getLocalizedMessage();
		} finally {

			em.close();
		}

		return "Data inserted into DB successfully";
	}

	public HibernateModel getDataDB(int id) {
		HibernateModel hm2 = null;
		try {

			log.info("Begin Transaction");

			hm2=em.find(HibernateModel.class, id);

			log.info("Data Assigned to the target");

			log.info("Data Fetch from DB successfully");

		} catch (Exception e) {
			e.getLocalizedMessage();
			log.info("Error while inserting into DB ");
			log.info("RollBacking the Transaction");
			return null;
		} finally {

			em.close();
		}
		
		
		return hm2;
	}

	public HibernateModel updateDataDB(HibernateModel req) {
		HibernateModel hm2 = null;
		try {

			log.info("Begin Transaction");

			hm2=em.merge(req);

			log.info("Data updated to the target");

			log.info("Data Modified into DB successfully");

		} catch (Exception e) {
			e.getLocalizedMessage();
			log.info("Error while updating into DB ");
			log.info("RollBacking the Transaction");
			return null;
		} finally {

			em.close();
		}
		
		
		return hm2;
	}

	public StringBuffer deleteDataDB(HibernateModel req) {
		
		try {

			log.info("Begin Transaction");
			
			HibernateModel entity = em.find(HibernateModel.class, req.getEmpId());
            if (entity != null) {
                
                em.remove(entity);
            } else {
                
                log.info("Entity with id " + req.getEmpId() + " not found.");
                throw new Exception();
            }

			

			log.info("Data removed from target");

			log.info("Data deleted from DB successfully");

		} catch (Exception e) {
			e.getLocalizedMessage();
			log.info("Error while deleting into DB ");
			log.info("RollBacking the Transaction");
			return new StringBuffer("Entity with id " + req.getEmpId() + " not found.");
		} finally {

			em.close();
		}
		
		
		return new StringBuffer("Data deleted from DB successfully");
	}

	public List<HibernateModel> getAllDataDB() {
		List<HibernateModel> result = new ArrayList<>();
		
		try {

			log.info("Begin Transaction");

			result= em.createQuery("FROM HibernateModel t1 JOIN t1.address t2",HibernateModel.class).getResultList();

			log.info("All Data Assigned to the target");

			log.info("Data Fetch from DB successfully");
			
		} catch (Exception e) {
			e.getLocalizedMessage();
			log.info("Error while fetching all from DB "+e.getLocalizedMessage());
			log.info("RollBacking the Transaction");
			return result;
		} finally {

			em.close();
		}
		
		
		return result;
	}

}
