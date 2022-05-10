package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.EmployeeProject;
import com.ntqsolution.pem.repository.ProjectEmployeeRepository;
import com.ntqsolution.pem.service.ProjectEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService {
    @Autowired
    private ProjectEmployeeRepository projectEmployeeRepository;

    @Override
    public List<EmployeeProject> getAllEmployeeByProjectId(Long id) {
        return projectEmployeeRepository.getAllByProjectId(id);
    }

    @Override
    public List<EmployeeProject> getAllProjectByEmployeeId(Long id) {
        return projectEmployeeRepository.getAllByEmployeeId(id);
    }

    @Override
    public void saveOrUpdateProjectEmployee(EmployeeProject employeeProject) {
        projectEmployeeRepository.saveAndFlush(employeeProject);
    }
}
