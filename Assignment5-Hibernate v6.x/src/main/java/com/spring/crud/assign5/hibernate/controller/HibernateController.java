package com.spring.crud.assign5.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crud.assign5.hibernate.model.HibernateModel;
import com.spring.crud.assign5.hibernate.service.HibernateService;


@RestController
@RequestMapping("/company")
	public class HibernateController {

		private static final Log log = LogFactory.getLog(HibernateController.class);

		@Autowired
		HibernateService hService;
	
		@GetMapping(value = "/viewEmployees",produces="application/json") // findAll
		public ResponseEntity<List<HibernateModel>> getEmployees() {
			log.info("Initiating FindAll Operation");

			List<HibernateModel> hm = null;

			try {
				
				 hm=hService.getAllData();
				 if(hm.equals(null)) {
					 log.error("Data not available in DB or check the DB schema is correct ");
					 log.error("Error while fetching the record from DB");
					 throw new Exception();
				 }

			} catch (Exception e) {
				e.getLocalizedMessage();
				log.error("Data not available in DB or check the DB schema is correct ");
				log.error("Error while fetching all records from DB");
				
				return new ResponseEntity<>(hm,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("FindAll Operation Completed");
			return new ResponseEntity<>(hm,HttpStatus.OK);
		}

		@GetMapping(value="/viewEmployee/{empId}",produces="application/json") // find
		public ResponseEntity<HibernateModel> getEmployee(@PathVariable("empId") int Id) {

			log.info("Initiating Find Operation");
			HibernateModel hm = null;

			try {
				
				 hm=hService.getData(Id);
				 if(hm.equals(null)) {
					 log.error("Data not available in DB or check the DB schema is correct ");
					 log.error("Error while fetching the record from DB");
					 throw new Exception();
				 }

			} catch (Exception e) {
				e.getLocalizedMessage();
				log.error("Data not available in DB or check the DB schema is correct ");
				log.error("Error while fetching the record from DB");
				HibernateModel hm1 = new HibernateModel(hm);
				return new ResponseEntity<>(hm1,HttpStatus.INTERNAL_SERVER_ERROR);
			}

			log.info("Find Operation Completed");

			return new ResponseEntity<>(hm,HttpStatus.OK);
		}
		
		
		 

		@PostMapping(value = "/insertNewEmployee", consumes = "application/json", produces = "application/xml")
		public ResponseEntity<List<String>> insertEmployees(@RequestBody HibernateModel postReq) {
			
			List<String> ApiResList=new ArrayList<>();
			String ApiResponse;
			
			log.info("Initiating Post Operation");

			try {

				ApiResponse=hService.insertData(postReq);
				
				ApiResList.add(ApiResponse);
				 
				if (!ApiResponse .contains("Data inserted into DB successfully") ) {
					return new ResponseEntity<>(ApiResList, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} catch (Exception e) {
				e.getLocalizedMessage();
				return new ResponseEntity<>(ApiResList, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			log.info("Post Operation Completed");

			return  new ResponseEntity<>(ApiResList, HttpStatus.CREATED);
		}

		@PutMapping("/updateAllEmployee") // modifyAll
		public ResponseEntity<HibernateModel> updateEmployeeDetails(@RequestBody HibernateModel putReq) {

			log.info("Initiating Put Operation");
			HibernateModel hm = null;
			try {
				
				 hm=hService.updateData(putReq);
				 if(hm.equals(null)) {
					 log.error("No column modified ");
					 log.error("Error while updating the record into DB ");
					 throw new Exception();
				 }

			} catch (Exception e) {
				e.getLocalizedMessage();
				log.error("No column modified ");
				log.error("Error while updating the record into DB ");
				HibernateModel hm1 = new HibernateModel(hm);
				return new ResponseEntity<>(hm1,HttpStatus.INTERNAL_SERVER_ERROR);
			}

			log.info("put Operation Completed");

			return new ResponseEntity<>(hm,HttpStatus.ACCEPTED);
		}

		@PatchMapping("/updateEmployee") // modify
		public ResponseEntity<HibernateService> modifyEmployee(@RequestBody HibernateController putReq,@RequestParam String Id) {

			log.fatal("Initiating Patch Operation");

			try {

			} catch (Exception e) {
				e.getLocalizedMessage();
			}

			log.fatal("Patch Operation Completed");

			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}

		@DeleteMapping("/removeEmployee")
		public ResponseEntity<StringBuffer> deleteEmployee(@RequestBody HibernateModel DeleteReq ) {
			StringBuffer hm=null;
			log.info("Initiating Delete Operation");

			try {
				
				hm=hService.removeData(DeleteReq);
				
				 if(!"Data deleted from DB successfully".contentEquals(hm)) {
					 log.error("Data not available in DB or check the DB schema is correct ");
					 log.error("Error while deleting the record from DB");
					 throw new Exception();
				 }

			} catch (Exception e) {
				e.getLocalizedMessage();
				StringBuffer s1=new StringBuffer("Data not available in DB or check the DB schema is correct, "+" ");
				StringBuffer s2=new StringBuffer("Error while deleting the record from DB, ");
				log.error("Data not available in DB or check the DB schema is correct ");
				log.error("Error while deleting the record from DB");
				StringBuffer hm1 = new StringBuffer(s1.append(s2).append(hm));
				return new ResponseEntity<>(hm1,HttpStatus.INTERNAL_SERVER_ERROR);
			}

			log.info("Delete Operation Completed");

			return new ResponseEntity<>(hm,HttpStatus.OK);
		}

	}


