package com.spring.rest.multipart.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileUpload2GlobalExceptionHandler {

	public List<Object> fileSizeLimitExceeded(String originalFilename, long size) {

		List<Object> response = new ArrayList<>();
		if (size > 1024 * 100) {
			String message = originalFilename
					+ "=> Error : File Size Limit Exceeded !! File is too large to read,File is not accepted";
			response.add(message);
		}

		return response;
	}

	public List<Object> emptyFileContentException(String originalFilename, long size) {
		List<Object> response = new ArrayList<>();
		if (size == 0) {
			String message = originalFilename + "=> Error : No content found inside file to read";
			response.add(message);
		}
		return response;
	}

	public List<Object> invalidFileException(String originalFilename, long size) {
		List<Object> response = new ArrayList<>();

		String message = "Invalid File !! File is not acceptable try with other file type";
		String ContentTypes = "Acceptable file formats => .json, .xml, .csv, .jpg, .jpeg, .png, .pdf, .xlsx";

		response.add(message);
		response.add(ContentTypes);

		return response;
	}

	public List<Object> executorException(String errorMessage) {
		List<Object> response = new ArrayList<>();
		String message = " Error thrown from application : " + errorMessage;
		response.add(message);
		return response;
	}

	public List<Object> DBException(String errorMessage) {
		List<Object> response = new ArrayList<>();
		String message = " Error thrown from DB : " + errorMessage;
		response.add(message);
		return response;
	}

	public List<Object> parserException(String errorMessage) {
		List<Object> response = new ArrayList<>();
		String message = " Error in parsing : " + errorMessage;
		response.add(message);
		return response;
	}

}
