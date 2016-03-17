package com.eason.spring.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class DefalutInterceptor extends HandlerInterceptorAdapter{
	private static final Log LOGGER = LogFactory.getLog(DefalutInterceptor.class);

	private boolean doValidation;
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		if(!doValidation){
			LOGGER.info("validation skiped!");
			return true;
		}
		LOGGER.info("This is DefalutInterceptor");
		//throw new Exception("signature is not correct");
		return true;
	}
	public boolean isDoValidation() {
	    return doValidation;
    }
	public void setDoValidation(boolean doValidation) {
	    this.doValidation = doValidation;
    }
}
