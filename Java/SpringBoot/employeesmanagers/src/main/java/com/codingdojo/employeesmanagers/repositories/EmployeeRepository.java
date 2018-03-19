package com.codingdojo.employeesmanagers.repositories;

import com.codingdojo.employeesmanagers.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    List<Employee> findAll();

    List<Employee> findEmployeesByManagerFirstName(String managerFirstName);

    Employee findEmployeesByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);
}
