package com.eason.spring.mvc.domain;

public class ExceptionResponse extends Response {

	private final String message;

	public ExceptionResponse(String message) {
		super(1);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
