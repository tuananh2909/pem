package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.Role;
import com.ntqsolution.pem.repository.RoleRepository;
import com.ntqsolution.pem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElseGet(Role::new);
    }
}
