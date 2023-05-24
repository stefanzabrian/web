package com.obsm.web.controller.project;

import com.obsm.web.model.Project;
import com.obsm.web.model.constant.ProjectCategory;
import com.obsm.web.model.constant.ProjectPriority;
import com.obsm.web.model.constant.ProjectStatus;
import com.obsm.web.model.constant.ProjectType;
import com.obsm.web.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

}
