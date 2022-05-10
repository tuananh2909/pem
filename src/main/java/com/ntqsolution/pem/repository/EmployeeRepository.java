package com.ntqsolution.pem.repository;

import com.ntqsolution.pem.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    long countEmployeesByStatusId(Long statusID);

    List<Employee> getAllEmployeesByStatusId(Long statusID);

    Page<Employee> getAllByNameLikeIgnoreCase(Pageable pageable, String keyword);
}
