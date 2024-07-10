package com.example.plugin.controller;

import com.example.plugin.model.Employee;
import com.example.plugin.repository.EmployeeRepository;
import com.example.plugin.service.EmployeeService;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employeeForm")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeForm";
    }

    @PostMapping("/employeeForm")
    public String submitEmployeeForm(@ModelAttribute Employee employee, Model model) {
        employeeService.saveEmployee(employee);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "result";
    }

//    @Autowired
//    private EmployeeRepository employeeRepository;

//    @Autowired
//    private VelocityEngine velocityEngine;
//
//    @GetMapping("/employeeForm")
//    public void showEmployeeForm(HttpServletResponse response) throws Exception {
//        VelocityContext context = new VelocityContext();
//        context.put("employee", new Employee());
//
//        Template template = velocityEngine.getTemplate("templates/employeeForm.vtl");
//
//        StringWriter writer = new StringWriter();
//        template.merge(context, writer);
//
//        response.setContentType("text/html");
//        response.getWriter().write(writer.toString());
//    }
//
//    @PostMapping("/saveEmployee")
//    public String saveEmployee(@ModelAttribute Employee employee) {
//        employeeRepository.save(employee);
//        return "redirect:/employeeForm";
//    }

}
