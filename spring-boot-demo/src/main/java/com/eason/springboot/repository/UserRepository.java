package com.eason.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eason.springboot.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User getByUsername(String username);
	
}
