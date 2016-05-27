package com.eason.spring.mvc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.spring.mvc.config.DateEditor;
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
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  
	                              ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());  
	}  
}
