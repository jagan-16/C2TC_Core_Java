package com.mall.employee;

import com.mall.employee.entity.Employee;
import com.mall.employee.repository.IEmployeeRepository;
import com.mall.employee.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * =========================================================
 * EmployeeServiceTest - Unit Tests (TDD Approach)
 * =========================================================
 * Tests follow the TDD cycle from the PPT (Slide 5):
 *   1. Identify test cases for each module
 *   2. Write failing test cases (RED)
 *   3. Implement to make tests pass (GREEN)
 *
 * Using Mockito to mock the repository layer.
 */
class EmployeeServiceTest {

    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee sampleEmployee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleEmployee = new Employee();
        sampleEmployee.setId(1);
        sampleEmployee.setName("Ravi Kumar");
        sampleEmployee.setDob(LocalDate.of(1995, 6, 15));
        sampleEmployee.setSalary(35000.0f);
        sampleEmployee.setAddress("12, Anna Nagar, Chennai");
        sampleEmployee.setDesignation("Cashier");
        sampleEmployee.setShopId(1L);
    }

    @Test
    @DisplayName("TC01: Add valid employee - should return saved employee")
    void TC01_addEmployee_validEmployee_shouldReturnSavedEmployee() {
        when(employeeRepository.save(sampleEmployee)).thenReturn(sampleEmployee);
        Employee result = employeeService.addEmployee(sampleEmployee);
        assertNotNull(result);
        assertEquals("Ravi Kumar", result.getName());
        assertEquals("Cashier", result.getDesignation());
        verify(employeeRepository, times(1)).save(sampleEmployee);
        System.out.println("TC01 PASSED: Valid employee added successfully.");
    }

    @Test
    @DisplayName("TC02: Add employee with null name - should throw exception")
    void TC02_addEmployee_nullName_shouldThrowException() {
        sampleEmployee.setName(null);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.addEmployee(sampleEmployee)
        );
        assertEquals("Employee name cannot be null or empty.", ex.getMessage());
        verify(employeeRepository, never()).save(any());
        System.out.println("TC02 PASSED: Exception thrown for null name.");
    }

    @Test
    @DisplayName("TC03: Add null employee object - should throw exception")
    void TC03_addEmployee_nullObject_shouldThrowException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.addEmployee(null)
        );
        assertEquals("Employee object cannot be null.", ex.getMessage());
        verify(employeeRepository, never()).save(any());
        System.out.println("TC03 PASSED: Exception thrown for null object.");
    }

    @Test
    @DisplayName("TC04: Update existing employee - should return updated employee")
    void TC04_updateEmployee_existing_shouldReturnUpdated() {
        sampleEmployee.setSalary(40000.0f);
        sampleEmployee.setDesignation("Senior Cashier");
        when(employeeRepository.existsById(1)).thenReturn(true);
        when(employeeRepository.save(sampleEmployee)).thenReturn(sampleEmployee);
        Employee result = employeeService.updateEmployee(sampleEmployee);
        assertNotNull(result);
        assertEquals(40000.0f, result.getSalary());
        assertEquals("Senior Cashier", result.getDesignation());
        verify(employeeRepository, times(1)).save(sampleEmployee);
        System.out.println("TC04 PASSED: Employee updated successfully.");
    }

    @Test
    @DisplayName("TC05: Update non-existing employee - should throw RuntimeException")
    void TC05_updateEmployee_nonExisting_shouldThrowException() {
        when(employeeRepository.existsById(1)).thenReturn(false);
        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> employeeService.updateEmployee(sampleEmployee)
        );
        assertTrue(ex.getMessage().contains("not found"));
        verify(employeeRepository, never()).save(any());
        System.out.println("TC05 PASSED: Exception for non-existing employee.");
    }

    @Test
    @DisplayName("TC06: Search employee by valid ID - should return employee")
    void TC06_searchEmployee_validId_shouldReturnEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(sampleEmployee));
        Employee result = employeeService.searchEmployee(1L);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Ravi Kumar", result.getName());
        System.out.println("TC06 PASSED: Employee found by ID.");
    }

    @Test
    @DisplayName("TC07: Search employee by invalid ID - should return null")
    void TC07_searchEmployee_invalidId_shouldReturnNull() {
        when(employeeRepository.findById(99)).thenReturn(Optional.empty());
        Employee result = employeeService.searchEmployee(99L);
        assertNull(result);
        System.out.println("TC07 PASSED: Null returned for invalid ID.");
    }

    @Test
    @DisplayName("TC08: Delete employee by valid ID - should return true")
    void TC08_deleteEmployee_validId_shouldReturnTrue() {
        when(employeeRepository.existsById(1)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1);
        Boolean result = employeeService.deleteEmployee(1L);
        assertTrue(result);
        verify(employeeRepository, times(1)).deleteById(1);
        System.out.println("TC08 PASSED: Employee deleted successfully.");
    }

    @Test
    @DisplayName("TC09: Delete employee by invalid ID - should return false")
    void TC09_deleteEmployee_invalidId_shouldReturnFalse() {
        when(employeeRepository.existsById(99)).thenReturn(false);
        Boolean result = employeeService.deleteEmployee(99L);
        assertFalse(result);
        verify(employeeRepository, never()).deleteById(anyInt());
        System.out.println("TC09 PASSED: False for non-existing ID.");
    }

    @Test
    @DisplayName("TC10: Get all employees - should return list")
    void TC10_getAllEmployees_shouldReturnList() {
        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setName("Priya Sharma");
        emp2.setDesignation("Manager");
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(sampleEmployee, emp2));
        List<Employee> result = employeeService.getAllEmployees();
        assertNotNull(result);
        assertEquals(2, result.size());
        System.out.println("TC10 PASSED: All employees retrieved. Count: " + result.size());
    }

    @Test
    @DisplayName("TC11: Get employees by shop ID - should return filtered list")
    void TC11_getEmployeesByShop_shouldReturnFilteredList() {
        when(employeeRepository.findByShopId(1L)).thenReturn(List.of(sampleEmployee));
        List<Employee> result = employeeService.getEmployeesByShop(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getShopId());
        System.out.println("TC11 PASSED: Employees filtered by shop ID.");
    }
}
