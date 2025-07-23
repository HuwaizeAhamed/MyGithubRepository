package com.spring.rest.multipart.controller;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest.multipart.exception.FileContentException;
import com.spring.rest.multipart.exception.FileLimitException;
import com.spring.rest.multipart.exception.FileNotPresentException;
import com.spring.rest.multipart.exception.GlobalExceptions;
import com.spring.rest.multipart.model.FileUploadModel;
import com.spring.rest.multipart.service.FileUploadService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	FileUploadService fileuplservice;
	@Autowired
	FileUploadModel fileuplmodel;

	@PostMapping(value = "/upload", consumes = "multipart/form-data", produces = "application/json")
@Transactional(rollbackOn = { Exception.class, FileLimitException.class, FileNotPresentException.class,FileContentException.class })
	
	public ResponseEntity<FileUploadModel> fileUploadConsumer(@RequestPart List<MultipartFile> files)
			throws FileLimitException, FileNotFoundException, FileContentException, Exception {
		
		System.out.println("File Upload Initiated");

		List<String> contentTypes = Arrays.asList("application/json", "application/xml", "application/csv",
				"text/plain", "application/octet-stream", "application/pdf", "image/jpeg", "image/png", "image/jpg");

		for (MultipartFile file : files) {

			System.out.println(file.getContentType());
			System.out.println(file.getSize());

			if (file.getSize() > 1024 * 500) {
				throw new FileLimitException(GlobalExceptions.File_LIMIT_EXCEEDED);
			} 
			
			else if (file.getSize() == 0) {
				throw new FileNotPresentException(GlobalExceptions.FILE_NOT_FOUND);
			}
			else if (!contentTypes.contains(file.getContentType())) {
				throw new FileContentException(GlobalExceptions.INVALID_FILE_CONTENT_TYPE + " , Allows only : " + contentTypes);
			}
			else {
				fileuplmodel = fileuplservice.parseUploadedFile(file);
			}
		}
		
		
		System.out.println("File Uploaded Successfully Completed");
		return new ResponseEntity<>(fileuplmodel, HttpStatus.OK);

	}
}
