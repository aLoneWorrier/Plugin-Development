package com.example.plugin;

import com.example.plugin.model.Employee;
import com.example.plugin.repository.EmployeeRepository;
import com.example.plugin.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("IT");
        employee.setGender("Male");

        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertThat(savedEmployee.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("IT");
        employee.setGender("Male");

        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        List<Employee> employees = employeeService.getAllEmployees();
        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getName()).isEqualTo("John Doe");
    }
}
