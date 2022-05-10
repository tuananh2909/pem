package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.StatusProject;

import java.util.List;

public interface StatusProjectService {
    List<StatusProject> getAllStatus();

    StatusProject findStatusById(Long id);
}
