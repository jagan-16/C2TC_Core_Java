package com.mall.employee.repository;

import com.mall.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * =========================================================
 * IEmployeeRepository
 * =========================================================
 * Repository interface for Employee data access.
 * Extends JpaRepository which provides built-in CRUD methods.
 *
 * From Class Design (PPT Slide 10):
 *   IEmployeeRepository
 *   + addEmployee    (Employee employee) : Employee
 *   + updateEmployee (Employee employee) : Employee
 *   + searchEmployee (long id)           : Employee
 *   + deleteEmployee (long id)           : Boolean
 *
 * JpaRepository already provides:
 *   - save(entity)        → addEmployee / updateEmployee
 *   - findById(id)        → searchEmployee
 *   - deleteById(id)      → deleteEmployee
 *   - findAll()           → getAllEmployees
 *
 * @RepositoryRestResource(exported = false) prevents Spring Data REST
 * from auto-exposing this repository as REST endpoints (we use our
 * own EmployeeController instead).
 */
@Repository
@RepositoryRestResource(exported = false)
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Find all employees belonging to a specific shop.
     * Custom query method using Spring Data naming convention.
     *
     * @param shopId the ID of the shop
     * @return List of employees in that shop
     */
    List<Employee> findByShopId(long shopId);

    /**
     * Find all employees with a specific designation.
     *
     * @param designation the designation/role (e.g. "Manager", "Cashier")
     * @return List of employees with that designation
     */
    List<Employee> findByDesignation(String designation);
}
