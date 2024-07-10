package com.example.plugin.controller;

import com.example.plugin.model.Employee;
import com.example.plugin.service.EmployeeService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private VelocityEngine velocityEngine;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testShowEmployeeForm() throws Exception {
        VelocityContext context = new VelocityContext();
        context.put("employee", new Employee());

        Template template = new Template(); // Use a dummy template for this test
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        when(velocityEngine.getTemplate("templates/employeeForm.vtl")).thenReturn(template);

        mockMvc.perform(get("/employeeForm"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("Engineering");
        employee.setGender("Male");

        mockMvc.perform(post("/saveEmployee")
                        .param("name", employee.getName())
                        .param("email", employee.getEmail())
                        .param("department", employee.getDepartment())
                        .param("gender", employee.getGender()))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string("Saved"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    public void testGetEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("Engineering");
        employee.setGender("Male");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        VelocityContext context = new VelocityContext();
        context.put("employees", Arrays.asList(employee));

        Template template = new Template(); // Use a dummy template for this test
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        when(velocityEngine.getTemplate("templates/result.vtl")).thenReturn(template);

        mockMvc.perform(get("/result"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }
}
