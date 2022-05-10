package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    Role findRoleById(Long id);
}
