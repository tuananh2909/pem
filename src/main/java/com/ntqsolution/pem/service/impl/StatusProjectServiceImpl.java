package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.StatusProject;
import com.ntqsolution.pem.repository.StatusProjectRepository;
import com.ntqsolution.pem.service.StatusProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusProjectServiceImpl implements StatusProjectService {
    @Autowired
    private StatusProjectRepository statusProjectRepository;

    @Override
    public List<StatusProject> getAllStatus() {
        return statusProjectRepository.findAll();
    }

    @Override
    public StatusProject findStatusById(Long id) {
        Optional<StatusProject> statusProjectOptional = statusProjectRepository.findById(id);
        return statusProjectOptional.orElseGet(StatusProject::new);
    }
}
