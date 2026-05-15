package com.tns.onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

	private String address;
	private ShoppingCart shoppingcart;
	private List<Order> orders ;
	
	
	public Customer(int userId, String userName, String email, String address) {
		super(userId , userName , email);
		this.address = address;
		this.shoppingcart = new ShoppingCart();
		this.orders = new ArrayList<>();
	}
	
	public String getAddress() {
		return address;
	}
	public ShoppingCart getShoppingcart() {
		return shoppingcart;
	}
	public List<Order> getOrder() {
		return orders;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	
	
	
}
