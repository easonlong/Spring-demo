package com.eason.spring.mvc.service;

public class Service {
	private String name;
	private String status;

	public Service(String name, String status) {
	    super();
	    this.name = name;
	    this.status = status;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
