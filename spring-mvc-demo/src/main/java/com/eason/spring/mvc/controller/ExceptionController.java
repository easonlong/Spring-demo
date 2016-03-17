package com.eason.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.spring.mvc.domain.Response;

@Controller
public class ExceptionController extends BaseController {

	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	@ResponseBody
	public Response exception() throws Exception,RuntimeException {
		int a=1/0;
		return null;
	}

}
