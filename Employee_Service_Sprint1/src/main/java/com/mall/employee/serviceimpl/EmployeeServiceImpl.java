package com.mall.employee.serviceimpl;

import com.mall.employee.entity.Employee;
import com.mall.employee.exception.EmployeeNotFoundException;
import com.mall.employee.repository.IEmployeeRepository;
import com.mall.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * =========================================================
 * EmployeeServiceImpl
 * =========================================================
 * Implementation of IEmployeeService.
 * Contains all the business logic for Employee operations.
 *
 * Annotations:
 *   @Service   - Marks this as a Spring Service Bean
 *   @Autowired - Injects the IEmployeeRepository dependency
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    // =========================================================
    // Add Employee
    // =========================================================

    /**
     * Saves a new Employee to the database.
     * Validates that name and designation are not null/empty.
     *
     * @param employee the Employee object to save
     * @return saved Employee with auto-generated ID
     * @throws IllegalArgumentException if required fields are missing
     */
    @Override
    public Employee addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee object cannot be null.");
        }
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty.");
        }
        if (employee.getDesignation() == null || employee.getDesignation().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee designation cannot be null or empty.");
        }
        return employeeRepository.save(employee);
    }

    // =========================================================
    // Update Employee
    // =========================================================

    /**
     * Updates an existing Employee record in the database.
     * Checks if the employee exists before updating.
     *
     * @param employee the Employee object with updated values
     * @return updated Employee object
     * @throws EmployeeNotFoundException if employee with given ID does not exist
     */
    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee object cannot be null.");
        }
        boolean exists = employeeRepository.existsById(employee.getId());
        if (!exists) {
            throw new EmployeeNotFoundException("Employee with ID " + employee.getId() + " not found. Cannot update.");
        }
        return employeeRepository.save(employee);
    }

    // =========================================================
    // Search Employee
    // =========================================================

    /**
     * Finds and returns an Employee by their ID.
     *
     * @param id the unique ID of the employee
     * @return Employee object if found, null if not found
     */
    @Override
    public Employee searchEmployee(long id) {
        Optional<Employee> optional = employeeRepository.findById((int) id);
        return optional.orElse(null);
    }

    // =========================================================
    // Delete Employee
    // =========================================================

    /**
     * Deletes an Employee by their ID.
     * Returns false if the employee does not exist.
     *
     * @param id the unique ID of the employee to delete
     * @return true if deleted successfully, false if not found
     */
    @Override
    public Boolean deleteEmployee(long id) {
        boolean exists = employeeRepository.existsById((int) id);
        if (!exists) {
            return false;
        }
        employeeRepository.deleteById((int) id);
        return true;
    }

    // =========================================================
    // Get All Employees
    // =========================================================

    /**
     * Retrieves all employees from the database.
     *
     * @return List of all Employee records
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // =========================================================
    // Get Employees By Shop
    // =========================================================

    /**
     * Retrieves all employees belonging to a specific shop.
     *
     * @param shopId the ID of the shop
     * @return List of employees in that shop
     */
    @Override
    public List<Employee> getEmployeesByShop(long shopId) {
        return employeeRepository.findByShopId(shopId);
    }
}
