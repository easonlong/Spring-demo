package com.eason.spring.cache;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eason.spring.dao.entity.User;

public class UserCacheTest {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");
	
	private UserService userService;

	@Before
	public void setup() {
		userService = (UserService) context.getBean("userService");
	}

	@Test
	public void testGetUser() throws Exception {
		System.out.println(userService.getUser("1").getUsername());
		System.out.println(userService.getUser("1").getUsername());
		System.out.println(userService.getUser("2").getUsername());
		System.out.println(userService.getUser("2").getUsername());
		System.out.println(userService.getUser("2").getUsername());
		System.out.println(userService.getUser("3").getUsername());
		Thread.sleep(10000);
		System.out.println(userService.getUser("1").getUsername());
		System.out.println(userService.getUser("2").getUsername());
		System.out.println(userService.getUser("3").getUsername());
	}

	@Test
	public void testUpdate() throws Exception {
		User user = userService.getUser("1");
		userService.getUser("2");
		user.setPassword("123456789");
		userService.updateUser(user);
		System.out.println(userService.getUser("1").getPassword());
		userService.getUser("2");
	}
	
	@Test
	public void testReload() throws Exception {
		System.out.println(userService.getUser("1").getUsername());
		System.out.println(userService.getUser("2").getUsername());
		System.out.println(userService.getUser("3").getUsername());
		Thread.sleep(100000);
	}
}
