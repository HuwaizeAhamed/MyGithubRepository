package com.spring.rest.multipart.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.rest.multipart.model.FileUploadModel;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	FileUploadModel fileuplmodel;

	@ExceptionHandler(FileContentException.class)
	public ResponseEntity<String> handleContent(FileContentException ex) {
//		fileuplmodel = new FileUploadModel("Invalid File Content-Type");
		return new ResponseEntity<>("Invalid File Content-Type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(FileLimitException.class)
	public ResponseEntity<String> handleFileLimit(FileLimitException ex) {
//		fileuplmodel = new FileUploadModel("File Limit Exceeded Accepts upto 500kb content only");
		return new ResponseEntity<>("File Limit Exceeded Accepts upto 500kb content only", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}

	@ExceptionHandler(FileNotPresentException.class)
	public ResponseEntity<String> handleNotFound(FileNotPresentException ex) {
//		fileuplmodel = new FileUploadModel("File Not Found");
		return new ResponseEntity<>("File Not Found", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<FileUploadModel> handleGeneric(GenericException ex) {
		return ResponseEntity.internalServerError().body(new FileUploadModel("File Upload Insertion Failure"));
	}

}
