package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.EmployeeProject;

import java.util.List;

public interface ProjectEmployeeService {
    List<EmployeeProject> getAllEmployeeByProjectId(Long id);

    List<EmployeeProject> getAllProjectByEmployeeId(Long id);

    void saveOrUpdateProjectEmployee(EmployeeProject employeeProject);
}
