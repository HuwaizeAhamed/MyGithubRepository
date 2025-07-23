package com.spring.rest.multipart.exception;

public class FileNotPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileNotPresentException(String message) {
		super(message);
	}

}
