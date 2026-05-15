package com.mall.employee.service;

import com.mall.employee.entity.Employee;

import java.util.List;

/**
 * =========================================================
 * IEmployeeService Interface
 * =========================================================
 * Service layer contract for Employee business logic.
 *
 * From Class Design (PPT Slide 9):
 *   IEmployeeService
 *   + addEmployee    (Employee employee) : Employee
 *   + updateEmployee (Employee employee) : Employee
 *   + searchEmployee (long id)           : Employee
 *   + deleteEmployee (long id)           : Boolean
 */
public interface IEmployeeService {

    /**
     * Add a new employee to the system.
     *
     * @param employee the Employee object to be saved
     * @return the saved Employee with generated ID
     */
    Employee addEmployee(Employee employee);

    /**
     * Update an existing employee's details.
     *
     * @param employee the Employee object with updated data
     * @return the updated Employee object
     */
    Employee updateEmployee(Employee employee);

    /**
     * Search / Fetch an employee by their ID.
     *
     * @param id the unique ID of the employee
     * @return the Employee object if found, null otherwise
     */
    Employee searchEmployee(long id);

    /**
     * Delete an employee from the system by their ID.
     *
     * @param id the unique ID of the employee to delete
     * @return true if deletion was successful, false otherwise
     */
    Boolean deleteEmployee(long id);

    /**
     * Retrieve all employees in the system.
     *
     * @return List of all Employee objects
     */
    List<Employee> getAllEmployees();

    /**
     * Retrieve all employees working in a specific shop.
     *
     * @param shopId the ID of the shop
     * @return List of employees in that shop
     */
    List<Employee> getEmployeesByShop(long shopId);
}
