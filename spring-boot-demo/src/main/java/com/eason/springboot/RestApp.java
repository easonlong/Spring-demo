package com.eason.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:switch.properties")
public class RestApp {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApp.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(RestApp.class, args);
		for (String profile : ctx.getEnvironment().getActiveProfiles()) {
			LOGGER.info("Using profile["+profile+"].");
		}
	}
}
