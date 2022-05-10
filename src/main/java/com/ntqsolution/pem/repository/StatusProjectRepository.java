package com.ntqsolution.pem.repository;

import com.ntqsolution.pem.entities.StatusProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusProjectRepository extends JpaRepository<StatusProject, Long> {
}
