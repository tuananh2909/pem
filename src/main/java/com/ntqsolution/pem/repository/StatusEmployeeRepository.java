package com.ntqsolution.pem.repository;

import com.ntqsolution.pem.entities.StatusEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEmployeeRepository extends JpaRepository<StatusEmployee, Long> {
}
