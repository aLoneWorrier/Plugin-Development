package com.example.plugin.service;

import com.example.plugin.model.Employee;
import com.example.plugin.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("Engineering");
        employee.setGender("Male");

        employeeService.saveEmployee(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("Engineering");
        employee.setGender("Male");

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }
}
