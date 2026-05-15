package com.tns.onlineshopping.services;
import com.tns.onlineshopping.entities.Admin;
import com.tns.onlineshopping.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
 
	private List<Admin> adminList = new ArrayList<>();
	private ProductService productservice;
	
	public AdminService(ProductService productservice) {
		this.productservice = productservice;
	}

	public void addAdmin(Admin admin) {
		adminList.add(admin);
	}
	

    public boolean isAdmin(int adminId) {
        return adminList.stream()
                .anyMatch(admin -> admin.getUserId() == adminId);
    }

	public List<Admin> getAdminList() {
		return adminList;
	}
	
	public void addProduct(int adminId , Product product) {
		validateAdmin(adminId);
		productservice.addProduct(product);
	}
	
	public void removeProduct(int adminId , int productId) {
		validateAdmin(adminId);
		productservice.removeProduct(productId);
	}
	
	public void validateAdmin(int adminId ) {
		if(!isAdmin(adminId)) {
			throw new RuntimeException("Access Denied");
		}
	}
	
	
}
