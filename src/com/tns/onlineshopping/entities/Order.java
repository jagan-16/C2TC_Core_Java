package com.tns.onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private int orderId;
	private Customer customer ;
	private List<OrderItem> items;
	private String status ;
	public Order(int orderId, Customer customer) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.items = new ArrayList<>();
		this.status = "Pending";
	}
	
	public void addProduct(Product product , int quantity ) {
				items.add(new OrderItem(product , quantity));
	}
	
	public int getOrderId() {
		return orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
	
}
