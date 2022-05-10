package com.ntqsolution.pem.controller;

import com.ntqsolution.pem.entities.Role;
import com.ntqsolution.pem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String roleList(ModelMap model) {
        List<Role> roleList = roleService.getAllRole();
        model.addAttribute("roleList", roleList);
        return "roles";
    }
}
