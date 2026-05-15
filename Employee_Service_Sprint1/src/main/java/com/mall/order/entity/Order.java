package com.mall.order.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * =========================================================
 * Order Entity (POJO Class)
 * =========================================================
 * Maps to the 'orders' table in PostgreSQL.
 *
 * Fields:
 *   - id          : int       (Primary Key, auto-generated)
 *   - customerId  : long      (FK reference to Customer)
 *   - shopId      : long      (FK reference to Shop)
 *   - orderDate   : LocalDate (Date when order was placed)
 *   - totalAmount : double    (Total order amount)
 *   - status      : String    (PENDING, CONFIRMED, DELIVERED, CANCELLED)
 *   - description : String    (Order description/items summary)
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_id", nullable = false)
    private long customerId;

    @Column(name = "shop_id", nullable = false)
    private long shopId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description")
    private String description;

    // =========================================================
    // Constructors
    // =========================================================

    public Order() {
    }

    public Order(int id, long customerId, long shopId, LocalDate orderDate,
                 double totalAmount, String status, String description) {
        this.id = id;
        this.customerId = customerId;
        this.shopId = shopId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.description = description;
    }

    // =========================================================
    // Getters and Setters
    // =========================================================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId; }

    public long getShopId() { return shopId; }
    public void setShopId(long shopId) { this.shopId = shopId; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // =========================================================
    // toString
    // =========================================================

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", shopId=" + shopId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
