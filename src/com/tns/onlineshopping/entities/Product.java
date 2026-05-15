package com.tns.onlineshopping.entities;

public class Product {
	
	private int  id ;
	private String name ;
	private double price;
	private int stockQuantity;
	
	public Product(){}
	
	public Product(int id, String name, double price, int stockQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}



}
