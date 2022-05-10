package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProject();

    long countAllProject();

    void saveOrUpdateProject(Project project);

    Project findProjectById(Long id);
}
