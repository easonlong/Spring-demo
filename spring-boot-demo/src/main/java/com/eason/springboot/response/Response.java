package com.eason.springboot.response;

abstract public class Response {

	private final int status;

	public Response(int status) {
		super();
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}
