package com.eason.spring.mvc.domain;

public class SuccessResponse extends Response {

	public final static Response SUCCESS = new SuccessResponse(0);

	private final Object response;

	private SuccessResponse() {
		super(0);
		this.response = null;
	}

	public SuccessResponse(Object response) {
		super(0);
		this.response = response;
	}

	public Object getResponse() {
		return response;
	}
}
