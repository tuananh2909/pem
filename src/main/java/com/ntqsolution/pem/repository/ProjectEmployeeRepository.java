package com.ntqsolution.pem.repository;

import com.ntqsolution.pem.entities.EmployeeProject;
import com.ntqsolution.pem.entities.EmployeeProjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<EmployeeProject, EmployeeProjectKey> {
    List<EmployeeProject> getAllByEmployeeId(Long id);

    List<EmployeeProject> getAllByProjectId(Long id);

    int countAllByEmployeeIdAndIsRejectFalse(Long employeeID);
}
