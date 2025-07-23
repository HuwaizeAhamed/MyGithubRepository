package com.spring.rest.jpa.crud.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.jpa.crud.controller.ProductController;
import com.spring.rest.jpa.crud.daorepo.MobileModelRepository;
import com.spring.rest.jpa.crud.daorepo.ProductRepository;
import com.spring.rest.jpa.crud.daorepo.TechRepository;
import com.spring.rest.jpa.crud.model.MobileModel;
import com.spring.rest.jpa.crud.model.Product;
import com.spring.rest.jpa.crud.model.TechProduct;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	ProductRepository pDao;
	@Autowired
	TechRepository tDao;
	@Autowired
	MobileModelRepository mDao;

//	EntityManager em=null;
//	public ProductService() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaCrud");
//		em = emf.createEntityManager();
//	}

	public static final Logger log = LogManager.getLogger(ProductController.class);

	@Transactional
	public String saveProduct(Product product) throws Exception {

		log.info("Service Task Initiated");
		
		product.setProductId(product.getProductId());
		product.setChocolates(product.getChocolates());
		product.setCheese(product.getCheese());
		product.setJuice(product.getJuice());
		product.setIceCream(product.getIceCream());
		product.setPanner(product.getPanner());
		
		log.info("Inserting parent table");

		try {
			pDao.save(product);
		} catch (Exception e) {
			log.error("error in inserting parent table " + e.getLocalizedMessage());
			log.error("data persistence failed " + e.getLocalizedMessage());
			return "Data Persistence Failed";
		}
		
		log.info("parent table inserted");
		
		log.info("Setting foreign key dependencies");
		
		try {
			for (TechProduct tech : product.getTechProducts()) {
				tech.setProduct(product);
				tDao.save(tech); // Save TechProduct

				for (MobileModel model : tech.getMobileModels()) {
					model.setTechProduct(tech);
					model.setProduct(product);
					mDao.save(model); // Save MobileModel

				}
			}
		} catch (Exception e) {
			log.error("error in inserting foreign columns " + e.getLocalizedMessage());
			return "Data Persistence Failed";
		}

		log.info("Service Task Completed");
		
		return "Data inserted/updated into DB Successfully - DB Persistence Completed";
	}

	@Transactional
	public Product getProduct(long pid) {
		Product p2=null;
		
		log.info("Service Task Initiated");
		try {
			p2=pDao.findById(pid).orElseThrow(()-> new Exception("Error thrown in service "));
			
			log.info("Setting Json Serialization");
			for (TechProduct tech : p2.getTechProducts()) {
			    tech.setProduct(p2);
			    for (MobileModel model : tech.getMobileModels()) {
			        model.setTechProduct(tech);
			        model.setProduct(p2);
			    }
			    
			}
			log.info("Setting Json Serialization Completed");
			
			log.info("ServiceResponse ==> " + p2);
		}catch(Exception e) {
			log.error("Error while fetching from DB " +e.getLocalizedMessage());
			return new Product("Error while fetching from DB ");
		}
		p2.setMessage("Success Product found");
		log.info("Service Task Completed");
		return p2;
	}

	@Transactional
	public String deleteProduct(long pid) {
		
		try {
			log.info("ServiceRepo Task Initiated");
			pDao.deleteById(pid);
			
			
		}catch(Exception e) {
			log.error("Error in service task"+e.getLocalizedMessage());
			return null;
		}
		log.info("ServiceRepo Task Completed");
		return "Data Removed from DB successfully";
	}

	@Transactional
	public List<Product> getAllProduct() {
		
		log.info("ServiceRepo Task Initiated");
		List<Product> p2=null;
		try {
			p2=pDao.findAll();
			log.info("Data Fetched from DB");
		}catch(Exception e) {
			log.error("Error while fetching data from DB "+e.getLocalizedMessage());
			return null;
		}
		
		log.info("ServiceRepo Task Completed");
		
		return p2;
	}

}
