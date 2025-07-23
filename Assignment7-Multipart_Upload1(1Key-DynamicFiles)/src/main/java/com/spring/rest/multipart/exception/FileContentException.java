package com.spring.rest.multipart.exception;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileContentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileContentException(String message) {
		super(message);
	}

}
