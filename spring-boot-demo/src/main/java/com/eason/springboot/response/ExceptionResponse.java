package com.eason.springboot.response;

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
