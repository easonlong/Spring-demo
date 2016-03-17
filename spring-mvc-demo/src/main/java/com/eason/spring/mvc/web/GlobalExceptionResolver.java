package com.eason.spring.mvc.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.eason.spring.mvc.domain.ExceptionResponse;
import com.eason.spring.mvc.domain.Response;


public class GlobalExceptionResolver extends ExceptionHandlerExceptionResolver {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionResolver.class);
	
	@Override
	protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
		Class<?> exceptionClazz = exception.getClass();
		ServletInvocableHandlerMethod method = this.getExceptionHandlerMethod(exceptionClazz);
		while (method == null) {
			exceptionClazz = exceptionClazz.getSuperclass();
			method = this.getExceptionHandlerMethod(exceptionClazz);
		}
		return method;
	}

	private ServletInvocableHandlerMethod getExceptionHandlerMethod(Class<?> clazz) {
		try {
			return new ServletInvocableHandlerMethod(this, this.getClass()
			        .getMethod("exception", new Class[] { clazz }));
		} catch (Exception e) {
			return null;
		}
	}

	@ResponseBody
	public Response exception(Exception e) {
		LOGGER.error("Handle Exception:",e);
		return new ExceptionResponse(e.getMessage());
	}

	@ResponseBody
	public Response exception(RuntimeException e) {
		LOGGER.error("Handle RuntimeException:",e);
		return new ExceptionResponse(e.getMessage());
	}
	
	@ResponseBody
	public Response exception(BindException e) {
		LOGGER.error("Handle BindException:", e);
		StringBuilder sb = new StringBuilder();
		for (FieldError error : e.getFieldErrors()) {
			sb.append("[ ").append(error.getField()).append(":").append(error.getDefaultMessage()).append(" ]").append("\n");
		}
		for(ObjectError error:e.getGlobalErrors()){
			sb.append("[ ").append(error.getDefaultMessage()).append(" ]").append("\n");
		}
		return new ExceptionResponse(sb.toString());
	}
}
