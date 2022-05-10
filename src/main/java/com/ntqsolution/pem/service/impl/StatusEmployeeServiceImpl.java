package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.StatusEmployee;
import com.ntqsolution.pem.repository.StatusEmployeeRepository;
import com.ntqsolution.pem.service.StatusEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusEmployeeServiceImpl implements StatusEmployeeService {
    @Autowired
    private StatusEmployeeRepository statusEmployeeRepository;

    @Override
    public StatusEmployee getStatusById(long id) {
        Optional<StatusEmployee> statusEmployeeOptional = statusEmployeeRepository.findById(id);
        return statusEmployeeOptional.orElseGet(StatusEmployee::new);
    }

    @Override
    public List<StatusEmployee> getAllStatus() {
        return statusEmployeeRepository.findAll();
    }
}
