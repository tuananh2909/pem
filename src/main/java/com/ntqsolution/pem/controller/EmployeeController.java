package com.ntqsolution.pem.controller;

import com.ntqsolution.pem.entities.*;
import com.ntqsolution.pem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    LanguageService languageService;

    @Autowired
    RoleService roleService;

    @Autowired
    StatusEmployeeService statusEmployeeService;

    @Autowired
    SettingService settingService;

    @Autowired
    ProjectEmployeeService projectEmployeeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String employeeList(ModelMap model) {
        List<Employee> employeeList = employeeService.getAllEmployee();
        List<Role> roleList = roleService.getAllRole();
        List<StatusEmployee> statusList = statusEmployeeService.getAllStatus();
        if (employeeList.isEmpty()) {
            model.addAttribute("empty", "Employee List is empty!!!");
        } else {
            model.addAttribute("employeeList", employeeList);
            model.addAttribute("roleList", roleList);
            model.addAttribute("statusEmList", statusList);
        }
        return "employees";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEmployee(ModelMap model) {
        List<Role> roles = roleService.getAllRole();
        List<Language> languages = languageService.getAllLanguage();
        model.addAttribute("roles", roles);
        model.addAttribute("languages", languages);
        model.addAttribute("newEmployee", new Employee());
        return "addEmployee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("newEmployee") Employee employee) {
        StatusEmployee status = statusEmployeeService.getStatusById(1);
        employee.setStatus(status);
        employee.setIsOut(false);
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable("id") Long id, ModelMap model) {
        Employee employee = employeeService.findEmployeeById(id);
        List<Role> roleList = roleService.getAllRole();
        List<Language> languageList = languageService.getAllLanguage();
        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        model.addAttribute("languageList", languageList);
        return "editEmployee";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employee") Employee employee) {
        employee.setIsOut(false);
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employeeService.deleteEmployeeById(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detailsEmployee(@PathVariable(name = "id") Long id, ModelMap model) {
        Employee employee = employeeService.findEmployeeById(id);
        List<EmployeeProject> employeeProjects = projectEmployeeService.getAllProjectByEmployeeId(id);
        if (employeeProjects.equals(null) || employeeProjects.size() == 0) {
            employeeProjects = null;
        }
        model.addAttribute("projectEmployees", employeeProjects);
        model.addAttribute("employee", employee);
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("languages", languageService.getAllLanguage());
        return "detailsEmployee";
    }

}
