package com.tns.onlineshopping.entities;

public class User {
	
	private int userId;
	private String userName;
	private String email;
	
	
	
	
	public User(int userId, String userName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}


	public int getUserId() {
		return userId;
	}


	public String getUserName() {
		return userName;
	}


	public String getEmail() {
		return email;
	}
	
	
	
	

}
