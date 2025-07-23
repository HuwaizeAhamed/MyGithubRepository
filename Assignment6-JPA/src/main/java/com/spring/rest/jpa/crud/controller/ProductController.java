package com.spring.rest.jpa.crud.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.jpa.crud.model.Product;
import com.spring.rest.jpa.crud.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	public static final Logger log = LogManager.getLogger(ProductController.class);

	@Autowired
	ProductService pService;

	@PostMapping(value = "/insertNewProduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<String>> saveProduct(@RequestBody Product product) {
		
		List<String> ApiResponseList=new ArrayList<>();
		String p = null;
		log.info("Post Method Initiated");

		try {

			p = pService.saveProduct(product);
			ApiResponseList.add(p);
			if (!p.equals("Data inserted/updated into DB Successfully - DB Persistence Completed")) {

				log.info("Generating Error Response Initiated");
				throw new Exception();
			}

		} catch (Exception e) {

			log.info("Exception Caught in Post Method " + e.getLocalizedMessage());
			return new ResponseEntity<>(ApiResponseList, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		log.info("Post Method Completed");
		return new ResponseEntity<>(ApiResponseList, HttpStatus.CREATED);

	}
	
	
	@GetMapping(value="/getProduct/{productId}",produces="application/json")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") long pid){
		
		Product p1=null;
		log.info("Get Method Initiated");
		try {
			p1=pService.getProduct(pid);
			
			if("Error while fetching from DB ".contentEquals(p1.getMessage())) {
				log.error("Error in Controller ");
				p1.setMessage("Data not available in DB for product Id : " +pid);
				throw new Exception();
			}
		}catch (Exception e) {
			
			return new ResponseEntity<>(p1,HttpStatus.NO_CONTENT);
		}
		
		log.info("Get Method Completed");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/getAllProducts",produces="application/json")
	public ResponseEntity<List<Product>> getProducts(){
		
		List<Product> p2=new ArrayList<>();
		log.info("Get All Method Initiated");
		Product p1=null;
		
		try {
			p2=pService.getAllProduct();
			
			if(p2.equals(null)) {
				p1=new Product("Data not available in DB - fetched 0 row/s");
				p2.add(p1);
				throw new Exception();
			}
			
		}catch(Exception e) {
			log.error("Error in Controller"+e.getLocalizedMessage());
			return new ResponseEntity<>(p2,HttpStatus.NO_CONTENT);
		}
		
		log.info("Get All Method Completed");
		
		return new ResponseEntity<>(p2,HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/removeProduct/{productId}",consumes="application/json")
	public ResponseEntity<String> removeProduct(@PathVariable ("productId") long pid){
		
		log.info("Delete Method Initiated");
		String msg=null;
		try {
			
			msg=pService.deleteProduct(pid);
			
			if(msg.equals(null)) {
				log.error("Generating Error Response");
				throw new Exception();
			}
			
		}catch(Exception e) {
			log.error("Error in Product Controller");
			msg="Error while removing/deleteing the product "+pid+" from DB"+e.getLocalizedMessage();
			return new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("Delete Method Completed");
		msg="Product ID : "+pid+" is removed/deleted from DB successfully";
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}

}
