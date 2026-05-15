package com.mall.customer.repository;

import com.mall.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * ICustomerRepository
 * =========================================================
 * Repository interface for Customer data access.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);

    List<Customer> findByNameContainingIgnoreCase(String name);

    List<Customer> findByLoyaltyPointsGreaterThanEqual(int points);
}
