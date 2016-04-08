package com.eason.springboot.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.eason.springboot.response.ExceptionResponse;
import com.eason.springboot.response.Response;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = BindException.class)
	@ResponseBody
	public Response exception(BindException e, WebRequest request) {
		StringBuilder sb = new StringBuilder();
		for (FieldError error : e.getFieldErrors()) {
			sb.append("[ ").append(error.getField()).append(":").append(error.getDefaultMessage()).append(" ]")
			        .append("\n");
		}
		for (ObjectError error : e.getGlobalErrors()) {
			sb.append("[ ").append(error.getDefaultMessage()).append(" ]").append("\n");
		}
		return new ExceptionResponse(sb.toString());
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Response exception(Exception e, WebRequest request) {
		return new ExceptionResponse(e.getMessage());
	}
}
