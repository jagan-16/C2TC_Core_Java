package com.tns.onlineshopping.entities;

public class OrderItem {
	
	private Product product ;
	private int quantity;
	private double priceAtPurchase;
	public OrderItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.priceAtPurchase = product.getPrice();
	}
	
	  public double getTotalPrice() {
	        return quantity * priceAtPurchase;
	    }
	
	
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
	

}
