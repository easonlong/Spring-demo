package com.eason.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.spring.mvc.domain.ExceptionResponse;
import com.eason.spring.mvc.domain.Response;

public class BaseController {
	
	private final static Log LOGGER = LogFactory.getLog(BaseController.class);

	@ExceptionHandler({Exception.class})
	public @ResponseBody Response handleException(Exception e, HttpServletRequest request) {
		LOGGER.info("handleEception:", e);
		return new ExceptionResponse(e.getMessage());
	}
	
	@ExceptionHandler({RuntimeException.class})
	public @ResponseBody Response handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		LOGGER.info("handleRuntimeException:", e);
		return new ExceptionResponse(e.getMessage());
	}
	
	@ExceptionHandler({BindException.class})
	public @ResponseBody Response handleBindException(BindException e, HttpServletRequest request){
		LOGGER.info("handleBindException:", e);
		StringBuilder sb = new StringBuilder();
		for (FieldError error : e.getFieldErrors()) {
			sb.append("[ ").append(error.getField()).append(":").append(error.getDefaultMessage()).append(" ]").append("\n");
		}
		return new ExceptionResponse(sb.toString());
	}
}
