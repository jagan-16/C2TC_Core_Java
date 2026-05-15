package com.mall.customer.serviceimpl;

import com.mall.customer.entity.Customer;
import com.mall.customer.exception.CustomerNotFoundException;
import com.mall.customer.repository.ICustomerRepository;
import com.mall.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * CustomerServiceImpl
 * =========================================================
 * Implementation of ICustomerService.
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer object cannot be null.");
        }
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer object cannot be null.");
        }
        boolean exists = customerRepository.existsById(customer.getId());
        if (!exists) {
            throw new CustomerNotFoundException("Customer with ID " + customer.getId() + " not found. Cannot update.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer searchCustomer(long id) {
        Optional<Customer> optional = customerRepository.findById((int) id);
        return optional.orElse(null);
    }

    @Override
    public Boolean deleteCustomer(long id) {
        boolean exists = customerRepository.existsById((int) id);
        if (!exists) {
            return false;
        }
        customerRepository.deleteById((int) id);
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> optional = customerRepository.findByEmail(email);
        return optional.orElse(null);
    }

    @Override
    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
}
