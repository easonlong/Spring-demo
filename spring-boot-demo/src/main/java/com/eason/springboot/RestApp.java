package com.eason.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:switch.properties")
public class RestApp 
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(RestApp.class, args);
	}
}
