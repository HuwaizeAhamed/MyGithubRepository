package com.spring.rest.multipart.filedownload.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.multipart.filedownload.service.MultipartFileDownload_Service;

@RestController
@RequestMapping("/fileDownloader1")
public class MultipartFileDownload_Controller {

	@Autowired
	MultipartFileDownload_Service service;

	@GetMapping(value = "/producer/pdfDownloader/{location}", produces = "*/*")
	public ResponseEntity<byte[]> pdfFileDownloadController(@PathVariable("location") String location) {

		byte[] APIresponseObj = null;
		HttpHeaders headers = new HttpHeaders();

		try {
				APIresponseObj = service.generatePdfFileResponse(location);
		}
			
		catch (ClassNotFoundException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			return new ResponseEntity<>("Class Not Found Exception : Failed in File Generation ".getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		catch (SQLException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			String errorResponse="SQL Exception : Failed in File Generation [Available Locations - [Bangalore,Chennai,Delhi,Hyderabad,Kolkata,Mumbai,Pune]]";
			return new ResponseEntity<>(errorResponse.getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (IOException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			return new ResponseEntity<>("IO Exception : Failed in File Generation ".getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		ContentDisposition contentDisposition = ContentDisposition.attachment().filename("download_"+location+".pdf").build();

		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(contentDisposition);

		return new ResponseEntity<>(APIresponseObj, headers, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/producer/csvDownloader/{location}", produces = "*/*")
	public ResponseEntity<byte[]> csvFileDownloadController(@PathVariable("location") String location) {

		byte[] APIresponseObj = null;
		HttpHeaders headers = new HttpHeaders();

		try {
			APIresponseObj = service.generateCsvFileResponse(location);
		}
		
		catch (ClassNotFoundException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			return new ResponseEntity<>("Class Not Found Exception : Failed in File Generation ".getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		catch (SQLException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			String errorResponse="SQL Exception : Failed in File Generation [Available Locations - [Bangalore,Chennai,Delhi,Hyderabad,Kolkata,Mumbai,Pune]]";
			return new ResponseEntity<>(errorResponse.getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		ContentDisposition contentDisposition = ContentDisposition.inline().filename("download_"+location+".csv").build();

		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(contentDisposition);

		return new ResponseEntity<>(APIresponseObj, headers, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/producer/txtDownloader", produces = "*/*")
	public ResponseEntity<byte[]> txtFileDownloadController(@RequestBody String TextMessage) {
		
		byte[] APIresponseObj = null;
		HttpHeaders headers = new HttpHeaders();
		
		APIresponseObj = service.generateTxtFileResponse(TextMessage);
		
		ContentDisposition contentDisposition = ContentDisposition.inline().filename("download_"+System.currentTimeMillis()+".txt").build();

		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(contentDisposition);
		
		return new ResponseEntity<>(APIresponseObj, headers, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(value = "/producer/excelDownloader/{location}", produces = "*/*")
	public ResponseEntity<byte[]> excelFileDownloadController(@PathVariable("location") String location) {

		byte[] APIresponseObj = null;
		HttpHeaders headers = new HttpHeaders();

		try {
			APIresponseObj = service.generateExcelFileResponse(location);
		} 
		catch (ClassNotFoundException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			return new ResponseEntity<>("Class Not Found Exception : Failed in File Generation ".getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		catch (SQLException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			String errorResponse="SQL Exception : Failed in File Generation [Available Locations - [Bangalore,Chennai,Delhi,Hyderabad,Kolkata,Mumbai,Pune]]";
			return new ResponseEntity<>(errorResponse.getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (IOException e) {
			ContentDisposition contentDisposition = ContentDisposition.inline().filename("ErrorResponse_"+System.currentTimeMillis()+".err").build();
			headers.setContentDisposition(contentDisposition);
			return new ResponseEntity<>("IO Exception : Failed in File Generation ".getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ContentDisposition contentDisposition = ContentDisposition.inline().filename("download_"+location+".xlsx").build();

		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(contentDisposition);

		return new ResponseEntity<>(APIresponseObj, headers, HttpStatus.OK);
	}
}
