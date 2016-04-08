package com.eason.springboot.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.eason.springboot.interceptor.DefaultInterceptor;

@Configuration
public class RestAppConfigurer extends WebMvcConfigurationSupport {
	@Bean
	public AutoAssembleConverter customJackson2HttpMessageConverter() {
		return new AutoAssembleConverter();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
	}
	
	@Bean
	public DefaultInterceptor defaultInterceptor(){
		return new DefaultInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.defaultInterceptor());
	}
}
