package com.mall.user.entity;

import jakarta.persistence.*;

/**
 * =========================================================
 * User Entity (POJO Class)
 * =========================================================
 * Maps to the 'users' table in PostgreSQL.
 * Handles authentication and role-based access for the system.
 *
 * Fields:
 *   - id       : int    (Primary Key, auto-generated)
 *   - username : String (Login username, unique)
 *   - password : String (Login password)
 *   - email    : String (User email, unique)
 *   - role     : String (ADMIN, SHOP_OWNER, CUSTOMER, EMPLOYEE)
 *   - fullName : String (Display name)
 *   - active   : boolean (Account active status)
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "active")
    private boolean active = true;

    // =========================================================
    // Constructors
    // =========================================================

    public User() {
    }

    public User(int id, String username, String password, String email,
                String role, String fullName, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
        this.active = active;
    }

    // =========================================================
    // Getters and Setters
    // =========================================================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // =========================================================
    // toString
    // =========================================================

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", fullName='" + fullName + '\'' +
                ", active=" + active +
                '}';
    }
}
