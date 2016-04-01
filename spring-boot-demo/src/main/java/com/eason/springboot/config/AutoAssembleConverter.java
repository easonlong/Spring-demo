package com.eason.springboot.config;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.eason.springboot.response.SuccessResponse;


/**
 * Global wrapper for the response, the response data will be wrapped as SuccessResponse
 * @author longyaokun
 *
 */
public class AutoAssembleConverter extends MappingJackson2HttpMessageConverter {
	
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		super.writeInternal(object.getClass().isAssignableFrom(SuccessResponse.class) ? object : new SuccessResponse(object), outputMessage);
	}
}
