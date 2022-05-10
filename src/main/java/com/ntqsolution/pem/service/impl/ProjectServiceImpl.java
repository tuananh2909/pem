package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.Project;
import com.ntqsolution.pem.repository.ProjectRepository;
import com.ntqsolution.pem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public long countAllProject() {
        return projectRepository.count();
    }

    @Override
    public void saveOrUpdateProject(Project project) {
        projectRepository.saveAndFlush(project);
    }

    @Override
    public Project findProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        return projectOptional.orElseGet(Project::new);
    }
}
