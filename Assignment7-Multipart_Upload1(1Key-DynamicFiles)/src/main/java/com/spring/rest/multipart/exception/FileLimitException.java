package com.spring.rest.multipart.exception;

public class FileLimitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileLimitException(String message) {
		super(message);
	}
}
