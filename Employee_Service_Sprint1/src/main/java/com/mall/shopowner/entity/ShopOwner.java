package com.mall.shopowner.entity;

import jakarta.persistence.*;

/**
 * =========================================================
 * ShopOwner Entity (POJO Class)
 * =========================================================
 * Maps to the 'shop_owners' table in PostgreSQL.
 *
 * Fields:
 *   - id          : int    (Primary Key, auto-generated)
 *   - name        : String (Owner's full name)
 *   - email       : String (Contact email)
 *   - phone       : String (Contact phone)
 *   - shopName    : String (Name of the shop)
 *   - shopCategory: String (Category: Electronics, Clothing, Food, etc.)
 *   - address     : String (Shop address in the mall)
 */
@Entity
@Table(name = "shop_owners")
public class ShopOwner {

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

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "shop_category")
    private String shopCategory;

    @Column(name = "address")
    private String address;

    // =========================================================
    // Constructors
    // =========================================================

    public ShopOwner() {
    }

    public ShopOwner(int id, String name, String email, String phone,
                     String shopName, String shopCategory, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.shopName = shopName;
        this.shopCategory = shopCategory;
        this.address = address;
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

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getShopCategory() { return shopCategory; }
    public void setShopCategory(String shopCategory) { this.shopCategory = shopCategory; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // =========================================================
    // toString
    // =========================================================

    @Override
    public String toString() {
        return "ShopOwner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopCategory='" + shopCategory + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
