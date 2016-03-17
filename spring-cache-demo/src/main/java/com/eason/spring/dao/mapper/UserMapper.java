package com.eason.spring.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.eason.spring.dao.entity.User;

public interface UserMapper {

	public User getUser(@Param("uid") String uid);

	public void update(User user);
}
