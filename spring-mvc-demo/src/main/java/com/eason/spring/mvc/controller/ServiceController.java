package com.eason.spring.mvc.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.spring.mvc.service.Service;
import com.eason.spring.mvc.service.ServiceContext;

@Controller
@RequestMapping(value = "/service")
public class ServiceController extends BaseController{

	private final ServiceContext context;

	public ServiceController(ServiceContext context) {
		super();
		this.context = context;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Service service(@PathVariable String name) throws Exception {
		return this.context.getService(name);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Service> services(@RequestParam(value = "status", required = false, defaultValue = "") String status)
	        throws Exception {
		return this.context.getServices(status);
	}
}
