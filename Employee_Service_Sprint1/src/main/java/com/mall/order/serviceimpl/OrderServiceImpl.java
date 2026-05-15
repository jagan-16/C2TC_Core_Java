package com.mall.order.serviceimpl;

import com.mall.order.entity.Order;
import com.mall.order.exception.OrderNotFoundException;
import com.mall.order.repository.IOrderRepository;
import com.mall.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * OrderServiceImpl
 * =========================================================
 * Implementation of IOrderService.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object cannot be null.");
        }
        if (order.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Customer ID must be a positive number.");
        }
        if (order.getShopId() <= 0) {
            throw new IllegalArgumentException("Shop ID must be a positive number.");
        }
        if (order.getStatus() == null || order.getStatus().trim().isEmpty()) {
            order.setStatus("PENDING");
        }
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDate.now());
        }
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object cannot be null.");
        }
        boolean exists = orderRepository.existsById(order.getId());
        if (!exists) {
            throw new OrderNotFoundException("Order with ID " + order.getId() + " not found. Cannot update.");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order searchOrder(long id) {
        Optional<Order> optional = orderRepository.findById((int) id);
        return optional.orElse(null);
    }

    @Override
    public Boolean deleteOrder(long id) {
        boolean exists = orderRepository.existsById((int) id);
        if (!exists) {
            return false;
        }
        orderRepository.deleteById((int) id);
        return true;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByCustomer(long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getOrdersByShop(long shopId) {
        return orderRepository.findByShopId(shopId);
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
