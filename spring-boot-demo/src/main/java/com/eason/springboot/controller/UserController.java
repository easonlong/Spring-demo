package com.eason.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eason.springboot.entity.User;
import com.eason.springboot.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User get(String username) {
		return this.userRepository.getByUsername(username);
	}
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<User> getAll(String username) {
		return this.userRepository.findAll();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public User add(@Valid User user){
		return userRepository.save(user);
	}
}
