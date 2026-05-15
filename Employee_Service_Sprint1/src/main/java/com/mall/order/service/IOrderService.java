package com.mall.order.service;

import com.mall.order.entity.Order;

import java.util.List;

/**
 * =========================================================
 * IOrderService Interface
 * =========================================================
 * Service layer contract for Order business logic.
 */
public interface IOrderService {

    Order addOrder(Order order);

    Order updateOrder(Order order);

    Order searchOrder(long id);

    Boolean deleteOrder(long id);

    List<Order> getAllOrders();

    List<Order> getOrdersByCustomer(long customerId);

    List<Order> getOrdersByShop(long shopId);

    List<Order> getOrdersByStatus(String status);
}
