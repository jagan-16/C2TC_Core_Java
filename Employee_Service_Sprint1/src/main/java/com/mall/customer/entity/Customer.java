package com.mall.customer.entity;

import jakarta.persistence.*;

/**
 * =========================================================
 * Customer Entity (POJO Class)
 * =========================================================
 * Maps to the 'customers' table in PostgreSQL.
 *
 * Fields:
 *   - id            : int    (Primary Key, auto-generated)
 *   - name          : String (Customer's full name)
 *   - email         : String (Contact email, unique)
 *   - phone         : String (Contact phone)
 *   - address       : String (Residential address)
 *   - loyaltyPoints : int    (Reward/loyalty points earned)
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "loyalty_points")
    private int loyaltyPoints;

    // =========================================================
    // Constructors
    // =========================================================

    public Customer() {
    }

    public Customer(int id, String name, String email, String phone,
                    String address, int loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.loyaltyPoints = loyaltyPoints;
    }

    // =========================================================
    // Getters and Setters
    // =========================================================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }

    // =========================================================
    // toString
    // =========================================================

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
