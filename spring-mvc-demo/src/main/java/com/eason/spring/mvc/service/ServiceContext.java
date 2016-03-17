package com.eason.spring.mvc.service;

import java.util.List;

public interface ServiceContext {

	public Service getService(String name) throws Exception;

	public List<Service> getServices(String status) throws Exception;

}
