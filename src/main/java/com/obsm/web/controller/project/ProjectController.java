package com.obsm.web.controller.project;

import com.obsm.web.model.*;
import com.obsm.web.model.constant.*;
import com.obsm.web.service.ProjectService;
import com.obsm.web.service.ProjectTaskUserService;
import com.obsm.web.service.TaskService;
import com.obsm.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectTaskUserService projectTaskUserService;
    private final TaskService taskService;

    public ProjectController(UserService userService,
                             ProjectService projectService,
                             ProjectTaskUserService projectTaskUserService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.projectTaskUserService = projectTaskUserService;
        this.taskService = taskService;
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
    public List<Project> getProjects() {
        return projectService.findAll();
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

    @GetMapping("/viewAllProjects")
    public String showViewAllProjectsPage(Model model) {

        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);

        return "viewAllProjects";
    }

    @GetMapping("/viewProject/{id}")
    public String viewProjectPage(@PathVariable("id") int id,
                                  Model model
    ) {
        Project thisProject = projectService.findById(id)
                .orElseThrow();
        model.addAttribute("project", thisProject);

        List<ProjectTaskUser> projectTaskUsers = projectTaskUserService.findAllByProject(thisProject);
        Map<Task, UserProfile> userProfileByTask = new HashMap<>();
        for (ProjectTaskUser projectTaskUser : projectTaskUsers) {
            userProfileByTask.put(projectTaskUser.getTask(), projectTaskUser.getUserProfile());
        }
        model.addAttribute("taskMap", userProfileByTask);

        List<User> employees = userService.findAllByUserRole(UserRole.MODERATOR);
    model.addAttribute("employees", employees);

        return "viewProject";
    }

    @GetMapping("/updateProjectById/{id}")
    public String showUpdateProjectByIdPage(@PathVariable("id") int id,
                                    Model model) {
        Project projectFound = projectService.findById(id).orElseThrow();
        model.addAttribute("project", projectFound);

        return "updateProjectById";
    }

    @PostMapping("/updateProjectById/{id}")
    public String updateProjectById(@PathVariable("id") int id , Project project, BindingResult result)
    {
        if (result.hasErrors()){
            return "updateProjectById";
        }
        Project projectFound = projectService.findById(id).orElseThrow();
        projectFound.setId(project.getId());
        projectFound.setName(project.getName());
        projectFound.setPrice(project.getPrice());
        projectFound.setDescription(project.getDescription());
        projectFound.setType(project.getType());
        projectFound.setCategory(project.getCategory());
        projectFound.setStatus(project.getStatus());
        projectFound.setStartDate(project.getStartDate());
        projectFound.setEndDate(project.getEndDate());

        projectService.save(project);

        return "redirect:/viewProject/" + project.getId();
    }

    @GetMapping("/deleteProjectById/{id}")
    public String deleteProjectById(@PathVariable("id") int id) {
        Project projectFound = projectService.findById(id).orElseThrow();
        List<ProjectTaskUser> projectTaskUser = projectTaskUserService.findAllByProject(projectFound);

        for(ProjectTaskUser projectTU : projectTaskUser){
            taskService.deleteTask(projectTU.getTask());
            projectTaskUserService.deleteTaskUser(projectTU);
        }
        projectService.delete(projectFound);

        return "redirect:/viewAllProjects";
    }

}
