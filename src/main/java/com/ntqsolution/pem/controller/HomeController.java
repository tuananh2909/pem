package com.ntqsolution.pem.controller;

import com.ntqsolution.pem.service.EmployeeService;
import com.ntqsolution.pem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(ModelMap model) {

        return "index";
    }
}
