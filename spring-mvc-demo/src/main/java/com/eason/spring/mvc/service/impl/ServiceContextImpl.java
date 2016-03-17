package com.eason.spring.mvc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.eason.spring.mvc.service.Service;
import com.eason.spring.mvc.service.ServiceContext;

public class ServiceContextImpl implements ServiceContext {

	private final static Log LOGGER = LogFactory.getLog(ServiceContextImpl.class);

	private final static List<Service> EMPTY = Collections.unmodifiableList(new ArrayList<Service>());

	private final static Map<String, Service> SERVICES = new HashMap<String, Service>();

	static {
		SERVICES.put("service1", new Service("service1", "OK"));
		SERVICES.put("service2", new Service("service2", "OK"));
		SERVICES.put("service3", new Service("service3", "DOWN"));
	}

	@Override
	public Service getService(String name) throws Exception {
		return SERVICES.get(name);
	}

	@Override
	public List<Service> getServices(String status) throws Exception {
		if (SERVICES.isEmpty()) {
			return EMPTY;
		}
		List<Service> list = new ArrayList<Service>();
		for (Service service : SERVICES.values()) {
			if (StringUtils.isEmpty(status) || service.getStatus().equals(status))
				list.add(service);
		}
		return list;
	}

}
