package com.eason.spring.mvc.domain;

/**
 * @author kim 2015年10月8日
 */
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
