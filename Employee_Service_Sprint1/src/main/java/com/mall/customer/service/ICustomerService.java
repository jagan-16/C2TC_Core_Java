package com.mall.customer.service;

import com.mall.customer.entity.Customer;

import java.util.List;

/**
 * =========================================================
 * ICustomerService Interface
 * =========================================================
 * Service layer contract for Customer business logic.
 */
public interface ICustomerService {

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer searchCustomer(long id);

    Boolean deleteCustomer(long id);

    List<Customer> getAllCustomers();

    Customer getCustomerByEmail(String email);

    List<Customer> searchCustomersByName(String name);
}
