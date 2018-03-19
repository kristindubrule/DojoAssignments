package com.codingdojo.employeesmanagers.services;

import com.codingdojo.employeesmanagers.models.Employee;
import com.codingdojo.employeesmanagers.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> employeesOfManger(String manager) {
        return employeeRepository.findEmployeesByManagerFirstName(manager);
    }

    public Employee findByName(String firstName, String lastName) {
        return employeeRepository.findEmployeesByFirstNameEqualsAndLastNameEquals(firstName,lastName);
    }
}
