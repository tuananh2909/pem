package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.Employee;
import com.ntqsolution.pem.entities.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    void saveOrUpdateEmployee(Employee employee);

    long countAllEmployee();

    long countActiveEmployee(Long id);

    Employee findEmployeeById(Long id);

    void deleteEmployeeById(Employee employee);

    List<Employee> getAvailableEmployeeForProject(Project project);

    List<Employee> getActiveEmployee();
}
