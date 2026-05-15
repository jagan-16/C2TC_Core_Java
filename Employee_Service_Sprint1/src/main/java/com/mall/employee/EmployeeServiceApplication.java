package com.mall.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * =========================================================
 * Shopping Mall Management System
 * Sprint 1 - All Modules
 * =========================================================
 * Main entry point for the Spring Boot Application.
 *
 * Modules:
 *   1. Employee Module   - /api/employees
 *   2. ShopOwner Module  - /api/shopowners
 *   3. Admin Module      - /api/admins
 *   4. Order Module      - /api/orders
 *   5. Customer Module   - /api/customers
 *   6. User Module       - /api/users
 *
 * @SpringBootApplication combines:
 *   @Configuration    - Marks as configuration class
 *   @EnableAutoConfiguration - Auto-configures Spring beans
 *   @ComponentScan    - Scans for components in this package
 */
@SpringBootApplication(scanBasePackages = "com.mall")
@EntityScan(basePackages = "com.mall")
@EnableJpaRepositories(basePackages = "com.mall")
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
        System.out.println("=========================================================");
        System.out.println("  Shopping Mall Management System - Sprint 1 Started!    ");
        System.out.println("  All Modules running at: http://localhost:8081           ");
        System.out.println("=========================================================");
    }
}
