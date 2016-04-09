package com.eason.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DefaultInterceptor extends HandlerInterceptorAdapter {

	private final static Log LOGGER = LogFactory.getLog(DefaultInterceptor.class);

	@Value("${interceptor.on}")
	private boolean on;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (this.on) {
			LOGGER.info("DefaultInterceptor on");
		} else {
			LOGGER.info("DefaultInterceptor off");
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("debug is on");
		}
		return true;
	}
}
