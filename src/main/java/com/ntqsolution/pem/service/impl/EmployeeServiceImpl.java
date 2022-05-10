package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.Employee;
import com.ntqsolution.pem.entities.EmployeeProject;
import com.ntqsolution.pem.entities.Language;
import com.ntqsolution.pem.entities.Project;
import com.ntqsolution.pem.repository.EmployeeRepository;
import com.ntqsolution.pem.repository.ProjectEmployeeRepository;
import com.ntqsolution.pem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectEmployeeRepository projectEmployeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public long countAllEmployee() {
        return employeeRepository.count();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseGet(Employee::new);
    }

    @Override
    public void deleteEmployeeById(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getAvailableEmployeeForProject(Project project) {
        int limit_employee = 3;
        boolean isContainLanguage = false;
        boolean isExistEmployee = false;
        List<Employee> result = new ArrayList<>();
        Set<Language> languageSet = project.getLanguages();
        Set<EmployeeProject> employeeProjectSet = project.getProjectEmployees();
        List<Employee> allEmployee = getActiveEmployee();
        for (Employee employee : allEmployee) {
            int count = projectEmployeeRepository.countAllByEmployeeIdAndIsRejectFalse(employee.getId());
            if (employee.getRole().getId() != 1 && employee.getRole().getId() != 2 && employee.getRole().getId() != 3 && employee.getRole().getId() != 4 &&
                    employee.getRole().getId() != 6 && employee.getRole().getId() != 7 && employee.getRole().getId() != 8 && employee.getRole().getId() != 9) {
                for (Language language : employee.getLanguages()) {
                    if (languageSet.contains(language)) {
                        isContainLanguage = true;
                        break;
                    }
                }
            } else {
                isContainLanguage = true;
            }
            for (EmployeeProject projectEmployee : employeeProjectSet) {
                if (Objects.equals(projectEmployee.getEmployee().getId(), employee.getId())) {
                    isExistEmployee = true;
                    break;
                }
            }
            if (isContainLanguage && count < limit_employee && !isExistEmployee) {
                result.add(employee);
            }
            isContainLanguage = false;
            isExistEmployee = false;
        }
        return result;
    }

    @Override
    public List<Employee> getActiveEmployee() {
        return employeeRepository.getAllEmployeesByStatusId(1L);
    }


    @Override
    public long countActiveEmployee(Long id) {
        return employeeRepository.countEmployeesByStatusId(id);
    }
}
