package com.mall.admin.entity;

import jakarta.persistence.*;

/**
 * =========================================================
 * Admin Entity (POJO Class)
 * =========================================================
 * Maps to the 'admins' table in PostgreSQL.
 *
 * Fields:
 *   - id       : int    (Primary Key, auto-generated)
 *   - name     : String (Admin's full name)
 *   - email    : String (Admin email, unique)
 *   - phone    : String (Contact phone)
 *   - role     : String (SUPER_ADMIN, MALL_MANAGER, FLOOR_MANAGER)
 *   - password : String (Admin password)
 */
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "password", nullable = false)
    private String password;

    // =========================================================
    // Constructors
    // =========================================================

    public Admin() {
    }

    public Admin(int id, String name, String email, String phone,
                 String role, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
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

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // =========================================================
    // toString
    // =========================================================

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
