
package com.tns.onlineshopping.services;

import com.tns.onlineshopping.entities.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orderList = new ArrayList<>();
    private int orderCounter = 1;
   

   

    // ------------------ Place Order ------------------

    public Order placeOrder(Customer customer, List<OrderItem> items) {

        Order order = new Order(orderCounter++, customer);

        for (OrderItem item : items) {
            order.addProduct(item.getProduct(), item.getQuantity());
        }

        orderList.add(order);

        return order;
    }

    // ------------------ Get Orders by Customer ------------------

    public List<Order> getOrdersByCustomer(int customerId) {
        List<Order> result = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getCustomer().getUserId() == customerId) {
                result.add(order);
            }
        }

        return result;
    }

    // ------------------ Get All Orders ------------------

    public List<Order> getAllOrders() {
        return orderList;
    }

    // ------------------ Get Order by ID ------------------

    public Order getOrderById(int orderId) {
        return orderList.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    // ------------------ Update Order Status ------------------

    public void updateOrderStatus(int orderId, String newStatus) {

        Order order = getOrderById(orderId);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        String currentStatus = order.getStatus();

        // Pending → Completed
        if ("Completed".equalsIgnoreCase(newStatus)
                && "Pending".equalsIgnoreCase(currentStatus)) {

            for (OrderItem item : order.getItems()) {
                Product product = item.getProduct();
                int qty = item.getQuantity();

                if (product.getStockQuantity() < qty) {
                    throw new RuntimeException("Insufficient stock for " + product.getName());
                }

                product.setStockQuantity(product.getStockQuantity() - qty);
            }
        }
        
        // Pending → Cancelled (no stock change — never deducted)
        else if ("Cancelled".equalsIgnoreCase(newStatus)
                && "Pending".equalsIgnoreCase(currentStatus)) {
            // No stock restoration needed
        }


        // Completed → Cancelled (restore stock)
        else if ("Cancelled".equalsIgnoreCase(newStatus)
                && "Completed".equalsIgnoreCase(currentStatus)) {

            for (OrderItem item : order.getItems()) {
                Product product = item.getProduct();
                int qty = item.getQuantity();

                product.setStockQuantity(product.getStockQuantity() + qty);
            }
        }

        // Delivered → no stock change
        else if ("Delivered".equalsIgnoreCase(newStatus)
        		&& "Completed".equalsIgnoreCase(currentStatus)) {
            // no-op
        }

        else {
            throw new RuntimeException("Invalid status transition: "  
            							+ currentStatus + " → " + newStatus);
        }

        order.setStatus(newStatus);
    }
}