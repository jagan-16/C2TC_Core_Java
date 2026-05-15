package com.tns.onlineshopping.services;
import com.tns.onlineshopping.entities.Product;

import java.util.ArrayList;
import java.util.List;



public class ProductService {
     
	private List<Product> productList = new ArrayList<>();
	
	public void addProduct(Product product) {
		productList.add(product);
	}
	
	public void removeProduct(int id) {
		productList.removeIf(product -> product.getId() == id);
	}

	public List<Product> getProductList() {
		return productList;
	}
	
	public Product getProductById (int id) {
		return productList.stream()
				.filter(product -> product.getId() == id)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}
}

