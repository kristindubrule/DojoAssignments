package com.codingdojo.employeesmanagers.controllers;

import com.codingdojo.employeesmanagers.models.Employee;
import com.codingdojo.employeesmanagers.services.EmployeeService;
import org.apache.catalina.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("findEmployees")
    public String employeesForManager() {
        return "index";
    }

    @PostMapping("findEmployees")
    public String findEmployees(@RequestParam("managerFirstName") String managerFirstName, @RequestParam("managerLastName") String managerLastName, Model model) {
        Employee manager = employeeService.findByName(managerFirstName, managerLastName);
        model.addAttribute("manager",manager);
        return "index";
    }

    @PostMapping("findManager")
    public String findManager(@RequestParam("employeeFirstName") String employeeFirstName, @RequestParam("employeeLastName") String employeeLastName, Model model) {
        Employee employee = employeeService.findByName(employeeFirstName, employeeLastName);
        model.addAttribute("employee",employee);
        return "index";
    }
}
