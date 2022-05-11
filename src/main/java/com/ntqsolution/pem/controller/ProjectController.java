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
import java.util.Set;

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

//    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
//    public String detailsProject(@PathVariable("id") Long id, ModelMap model) {
//        Project project = projectService.findProjectById(id);
//        model.addAttribute("projects", project);
//        model.addAttribute("languages", languageService.getAllLanguage());
//        return "detailsProject";
//    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProject(@PathVariable("id") Long id, ModelMap model) {
        Project project = projectService.findProjectById(id);
        List<Employee> employee = employeeService.getAvailableEmployeeForProject(project);
        if (project.getProjectEmployees() == null || project.getProjectEmployees().size() == 0) {
            model.addAttribute("projectEmployee", null);
        } else {
            model.addAttribute("projectEmployee", projectEmployeeService.getAllEmployeeByProjectId(project.getId()));
        }
        model.addAttribute("projectEm", new EmployeeProject());
        model.addAttribute("project", project);
        model.addAttribute("employee", employee);
        return "editProject";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String addEmployeeToProject(@ModelAttribute EmployeeProject employeeProject) {
        employeeProject.setDateJoin(LocalDate.now());
        EmployeeProjectKey employeeProjectKey = new EmployeeProjectKey();
        employeeProjectKey.setEmployeeID(employeeProject.getEmployee().getId());
        employeeProjectKey.setProjectID(employeeProject.getProject().getId());
        employeeProject.setId(employeeProjectKey);
        employeeProject.getEmployee().setUpdateAt(LocalDate.now());
        employeeProject.getProject().setUpdateAt(LocalDate.now());
        employeeProject.setIsReject(false);
        projectEmployeeService.saveOrUpdateProjectEmployee(employeeProject);
        return "redirect:/projects/edit/" + employeeProject.getProject().getId();
    }

    @RequestMapping(value = "/close/{id}", method = RequestMethod.GET)
    public String closeProject(@PathVariable("id") Long id) {
        Project project = projectService.findProjectById(id);
        StatusProject statusProject = statusProjectService.findStatusById(2L);
        project.setStatus(statusProject);
        Set<EmployeeProject> employeeProjects = project.getProjectEmployees();
        for (EmployeeProject employeeProject : employeeProjects) {
            employeeProject.setDateOut(LocalDate.from(LocalDate.now()));
            employeeProject.setIsReject(true);
            projectEmployeeService.saveOrUpdateProjectEmployee(employeeProject);
        }
        projectService.saveOrUpdateProject(project);
        return "redirect:/projects/list";
    }
}
