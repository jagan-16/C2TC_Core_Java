package com.tns.onlineshopping.services;

import com.tns.onlineshopping.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customerList = new ArrayList<>();

    // ------------------ Create Customer ------------------

    public Customer createCustomer(int userId, String userName, String email, String address) {

        for (Customer c : customerList) {
            if (c.getUserId() == userId) {
                throw new RuntimeException("Customer with ID " + userId + " already exists");
            }
        }

        Customer customer = new Customer(userId, userName, email, address);
        customerList.add(customer);
        return customer;
    }

    // ------------------ Get Customer by ID ------------------

    public Customer getCustomerById(int userId) {
        return customerList.stream()
                .filter(c -> c.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    // ------------------ Get All Customers ------------------

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    // ------------------ Update Address ------------------

    public void updateAddress(int userId, String newAddress) {

        Customer customer = getCustomerById(userId);

        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + userId);
        }

        customer.setAddress(newAddress);
    }

    // ------------------ Remove Customer ------------------

    public void removeCustomer(int userId) {

        Customer customer = getCustomerById(userId);

        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + userId);
        }

        customerList.remove(customer);
    }
}