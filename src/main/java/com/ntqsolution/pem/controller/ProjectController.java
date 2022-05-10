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
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StatusProjectService statusProjectService;

    @Autowired
    private ProjectEmployeeService projectEmployeeService;

    @RequestMapping("/list")
    public String projectList(ModelMap model) {
        List<Project> projectList = projectService.getAllProject();
        List<StatusProject> statusProjectList = statusProjectService.getAllStatus();
        List<Language> languageList = languageService.getAllLanguage();
        if (projectList.isEmpty()) {
            model.addAttribute("projectEmpty", "Project List is empty!!!");
        } else {
            model.addAttribute("projectList", projectList);
            model.addAttribute("statusProjectList", statusProjectList);
            model.addAttribute("languageList", languageList);
        }
        return "forms";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProject(ModelMap model) {
        List<Language> languageList = languageService.getAllLanguage();
        model.addAttribute("languageList", languageList);
        model.addAttribute("newProject", new Project());
        return "addProject";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("newProject") Project project) {
        StatusProject statusProject = statusProjectService.findStatusById(1L);
        project.setStatus(statusProject);
        projectService.saveOrUpdateProject(project);
        return "redirect:/projects/list";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detailsProject(@PathVariable("id") Long id, ModelMap model) {
        Project project = projectService.findProjectById(id);
        model.addAttribute("projects", project);
        model.addAttribute("languages", languageService.getAllLanguage());
        return "detailsProject";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProject(@PathVariable("id") Long id, ModelMap model) {
        Project project = projectService.findProjectById(id);
        //List<Employee> employee = employeeService.getAvailableEmployeeForProject(project);
        List<Language> languageList = languageService.getAllLanguage();
//        if (project.getProjectEmployees() == null || project.getProjectEmployees().size() == 0) {
//            model.addAttribute("projectEmployee", null);
//        } else {
//            model.addAttribute("projectEmployee", projectEmployeeService.getAllEmployeeByProjectId(project.getId()));
//        }
//        model.addAttribute("projectEm", new EmployeeProject());
        model.addAttribute("languageList", languageList);
        model.addAttribute("project", project);
        //model.addAttribute("employee", employee);
        return "editProject";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProject(@ModelAttribute("employee") Project project /*@ModelAttribute EmployeeProject employeeProject*/) {
        /*employeeProject.setDateJoin(LocalDate.now());
        EmployeeProjectKey employeeProjectKey = new EmployeeProjectKey();
        employeeProjectKey.setEmployeeID(employeeProject.getEmployee().getId());
        employeeProjectKey.setProjectID(employeeProject.getProject().getId());
        employeeProject.setId(employeeProjectKey);
        projectEmployeeService.saveOrUpdateProjectEmployee(employeeProject);*/
        projectService.saveOrUpdateProject(project);
        //return "redirect:/projects/edit/" + employeeProject.getProject().getId();
        return "redirect:/projects/list";
    }
}
