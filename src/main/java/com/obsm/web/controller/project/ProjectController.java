package com.obsm.web.controller.project;

import com.obsm.web.model.Product;
import com.obsm.web.model.Project;
import com.obsm.web.model.constant.*;
import com.obsm.web.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/addProject")
    public String showAddProjectPage(Project project) {
        return "addProject";
    }

    @ModelAttribute("project")
    public Project project() {
        return new Project();
    }
    @ModelAttribute("projects")
    public List<Project> getProjects() {return projectService.findAll();}

    @ModelAttribute("categories")
    public ProjectCategory[] getProjectCategories() {
        return ProjectCategory.values();
    }

    @ModelAttribute("priorities")
    public ProjectPriority[] getProjectPriorities() {
        return ProjectPriority.values();
    }

    @ModelAttribute("types")
    public ProjectType[] getProjectTypes() {
        return ProjectType.values();
    }

    @ModelAttribute("statuses")
    public ProjectStatus[] getProjectStatuses() {
        return ProjectStatus.values();
    }

    @PostMapping("/addProject")
    public String addProduct(
            @ModelAttribute("project")
            @Valid
            Project project,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "/addProject";

        }

        projectService.create(project);

        return "redirect:/addProject?success";
    }

    @GetMapping("/viewAllProjects")
    public String showViewAllProjectsPage(Model model) {

        List<Project> projects = projectService.findAll();
        /*
        List<ProjectCategory> categories = Arrays.asList(ProjectCategory.values());
        List<ProjectPriority> priorities = Arrays.asList(ProjectPriority.values());
        List<ProjectStatus> statuses = Arrays.asList(ProjectStatus.values());
        List<ProjectType> types = Arrays.asList(ProjectType.values());*/

        model.addAttribute("projects", projects);
       /*
        model.addAttribute("categories", categories);
        model.addAttribute("priorities", priorities);
        model.addAttribute("statuses", statuses);
        model.addAttribute("types", types);*/

        return "viewAllProjects";
    }
}
