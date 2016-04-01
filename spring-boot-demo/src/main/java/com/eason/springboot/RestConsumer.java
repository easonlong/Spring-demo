package com.eason.springboot;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.eason.springboot.entity.User;

public class RestConsumer {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
        List<User> users = restTemplate.getForObject("http://localhost:8080/user/getall", List.class);
		System.out.println(users);
	}
}
