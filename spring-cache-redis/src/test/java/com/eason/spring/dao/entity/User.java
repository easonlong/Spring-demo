package com.eason.spring.dao.entity;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	private String uid;
	
	private String username;
	
	private String password;

	public User(String uid, String username, String password) {
	    super();
	    this.uid = uid;
	    this.username = username;
	    this.password = password;
    }

	public String getPassword() {
	    return password;
    }

	public void setPassword(String password) {
	    this.password = password;
    }

	public String getUsername() {
	    return username;
    }

	public void setUsername(String username) {
	    this.username = username;
    }

	public String getUid() {
	    return uid;
    }

	public void setUid(String uid) {
	    this.uid = uid;
    }
	
}
