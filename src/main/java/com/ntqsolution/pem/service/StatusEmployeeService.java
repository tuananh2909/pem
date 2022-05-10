package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.StatusEmployee;

import java.util.List;

public interface StatusEmployeeService {
    StatusEmployee getStatusById(long id);

    List<StatusEmployee> getAllStatus();
}
