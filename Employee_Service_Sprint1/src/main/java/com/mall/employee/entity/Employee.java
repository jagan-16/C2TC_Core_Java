package com.mall.employee.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * =========================================================
 * Employee Entity (POJO Class)
 * =========================================================
 * Maps to the 'employees' table in PostgreSQL.
 *
 * From Class Design (PPT Slide 8):
 *   Employee
 *   - id         : int
 *   - name       : String
 *   - dob        : LocalDate
 *   - salary     : float
 *   - address    : String
 *   - designation: String
 *   - shop_id    : Shop  (represented as shopId long for Sprint 1)
 *
 * Annotations:
 *   @Entity - Marks this as a JPA Entity (maps to DB table)
 *   @Table  - Specifies the table name in PostgreSQL
 *   @Id     - Marks the primary key
 *   @GeneratedValue - Auto-generates the ID (IDENTITY = auto-increment)
 *   @Column - Maps field to DB column
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "salary")
    private float salary;

    @Column(name = "address")
    private String address;

    @Column(name = "designation")
    private String designation;

    // Representing shop_id as a long for Sprint 1
    // In later sprints, this will be a @ManyToOne relationship with Shop entity
    @Column(name = "shop_id")
    private long shopId;

    // =========================================================
    // Constructors
    // =========================================================

    /**
     * Default no-arg constructor (required by JPA).
     */
    public Employee() {
    }

    /**
     * Parameterized constructor with all fields.
     */
    public Employee(int id, String name, LocalDate dob, float salary,
                    String address, String designation, long shopId) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.address = address;
        this.designation = designation;
        this.shopId = shopId;
    }

    // =========================================================
    // Getters and Setters
    // =========================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    // =========================================================
    // toString
    // =========================================================

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", designation='" + designation + '\'' +
                ", shopId=" + shopId +
                '}';
    }
}
