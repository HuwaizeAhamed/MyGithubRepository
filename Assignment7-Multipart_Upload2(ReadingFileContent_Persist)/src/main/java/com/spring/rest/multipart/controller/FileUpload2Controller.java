package com.spring.rest.multipart.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.spring.rest.multipart.exception.FileUpload2GlobalExceptionHandler;
import com.spring.rest.multipart.service.FileUpload2Service;

@RestController
@RequestMapping("fileupload2")
@Component
public class FileUpload2Controller {

	@Autowired
	FileUpload2Service service;
	@Autowired
	FileUpload2GlobalExceptionHandler exception;

	@PostMapping(value = "/ReadFilesContent", consumes = "multipart/form-data", produces = "application/json")

	public ResponseEntity<List<Object>> fileUploadConsumer(@RequestParam List<MultipartFile> myFile,
			@RequestParam String myText) {

		System.out.println("File Upload Initiated");

		List<Object> Response = new ArrayList<>();
		List<Object> AllResponse = new ArrayList<>();

		List<String> contentTypes = Arrays.asList("application/json", "application/xml", "text/csv", "text/plain",
				"application/octet-stream", "application/pdf", "image/jpeg", "image/png", "image/jpg",
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel");

		for (MultipartFile file : myFile) {

			System.out.println("\n\n --Reading All files-- \n\n");

			System.out.println(file.getContentType());
			System.out.println(file.getSize());

			if (file.getSize() > 1024 * 100) {
				AllResponse = exception.fileSizeLimitExceeded(file.getOriginalFilename(), file.getSize());
				return new ResponseEntity<>(AllResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			else if (file.getSize() == 0) {
				AllResponse = exception.emptyFileContentException(file.getOriginalFilename(), file.getSize());
				return new ResponseEntity<>(AllResponse, HttpStatus.BAD_REQUEST);
			}

			else if (!contentTypes.contains(file.getContentType())) {
				AllResponse = exception.invalidFileException(file.getOriginalFilename(), file.getSize());
				return new ResponseEntity<>(AllResponse, HttpStatus.NOT_ACCEPTABLE);
			}

			else {
				try {
					Response = service.readFileData(file.getContentType(), myText, file);
					AllResponse.addAll(Response);

				}

				catch (IOException io) {

					List<Object> response = new ArrayList<>();
					String Error = "IO : File not found Exception!!";
					response.add(Error);
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

				} catch (SAXException se) {

					List<Object> response = new ArrayList<>();
					String Error = "SAXException : Error while parsing xml!!";
					response = exception.parserException(Error);
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

				} catch (ParserConfigurationException pe) {

					List<Object> response = new ArrayList<>();
					String Error = "Parsing Exception!!";
					response = exception.parserException(Error);
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

				} catch (SQLException se) {

					List<Object> response = new ArrayList<>();
					String Error = "SQLException : Failed in DB";
					response = exception.DBException(Error);
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

				} catch (InterruptedException ie) {

					List<Object> response = new ArrayList<>();
					String Error = "Exception : Failed in controller method";
					response = exception.executorException(Error);
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

				}

			}

		}

		System.out.println("File Upload Completed");

		return new ResponseEntity<>(AllResponse, HttpStatus.OK);
	}

}
