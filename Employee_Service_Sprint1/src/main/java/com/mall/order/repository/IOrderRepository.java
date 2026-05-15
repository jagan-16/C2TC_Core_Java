package com.mall.order.repository;

import com.mall.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * =========================================================
 * IOrderRepository
 * =========================================================
 * Repository interface for Order data access.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface IOrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerId(long customerId);

    List<Order> findByShopId(long shopId);

    List<Order> findByStatus(String status);

    List<Order> findByCustomerIdAndStatus(long customerId, String status);
}
