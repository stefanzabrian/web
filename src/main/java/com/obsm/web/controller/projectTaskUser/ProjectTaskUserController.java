package com.obsm.web.controller.projectTaskUser;

import com.obsm.web.controller.dto.PTU_dto;
import com.obsm.web.model.*;
import com.obsm.web.model.constant.TaskCategory;
import com.obsm.web.model.constant.TaskStatus;
import com.obsm.web.model.constant.TaskStructure;
import com.obsm.web.model.constant.UserRole;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectTaskUserController {
    private final ProjectService projectService;
    private final ProjectTaskUserService projectTUService;
    private final UserService userService;
    private final TaskService taskService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Project projectFound = new Project();

    public ProjectTaskUserController(ProjectService projectService,
                                     ProjectTaskUserService projectTUService,
                                     UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.projectTUService = projectTUService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @ModelAttribute("structures")
    public List<TaskStructure> getStructures() {
        return List.of(TaskStructure.values());
    }

    @ModelAttribute("categories")
    public List<TaskCategory> getCategories() {
        return List.of(TaskCategory.values());
    }

    @ModelAttribute("statuses")
    public List<TaskStatus> getStatuses() {
        return List.of(TaskStatus.values());
    }

    @GetMapping("/addProjectTaskUser/{id}")
    public String showAddProjectTaskUserPage(@PathVariable("id") int id,
                                             Model model) {
        Optional<Project> project = projectService.findById(id);
        project.ifPresent(value -> projectFound = value);
        model.addAttribute("task", new Task());
        model.addAttribute("structures", List.of(TaskStructure.values()));
        model.addAttribute("categories", List.of(TaskCategory.values()));
        model.addAttribute("statuses", List.of(TaskStatus.values()));
        return "addProjectTaskUser";
    }

    @PostMapping("/addProjectTaskUser")
    public String addTaskToProject(Task task,
                                   BindingResult result
    ) {
        if (result.hasErrors()) {
            return "/addProjectTaskUser";
        }

        taskService.save(task);

        ProjectTaskUser projectTaskUser = new ProjectTaskUser();
        projectTaskUser.setProject(projectFound);
        projectTaskUser.setTask(task);
        projectTUService.save(projectTaskUser);


        return "redirect:/viewProject/" + projectFound.getId();
    }

    @GetMapping("/viewTask/{id}")
    public String showViewTaskByIdPage(@PathVariable("id") int id,
                                       Model model) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
        model.addAttribute("task", task);

        return "viewTask";
    }

    @GetMapping("/updateTask/{id}")
    public String showUpdateTaskByIdPage(@PathVariable("id") int id,
                                         Model model) {
        Task task = taskService.findById(id)
                .orElseThrow();
        ProjectTaskUser projectTU = projectTUService.findByTask(task)
                .orElseThrow();
        PTU_dto ptuDto = new PTU_dto();
        ptuDto.setId(projectTU.getId());
        ptuDto.setTaskNumber(task.getNumber());
        ptuDto.setTaskName(task.getName());
        ptuDto.setTaskStructure(task.getStructure());
        ptuDto.setTaskCategory(task.getCategory());
        ptuDto.setTaskStatus(task.getStatus());
        ptuDto.setComments(task.getComments());
        ptuDto.setCompletionDate((task.getCompletionDate().toString().substring(0, 10)));
        User userFound = userService.findByUserProfile(projectTU.getUserProfile())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        ptuDto.setUserProfile(userFound.getUserProfile());

        model.addAttribute("ptuDto", ptuDto);

        List<User> employees = userService.findAllByUserRole(UserRole.MODERATOR);

        List<UserProfile> userProfiles = new ArrayList<>();
        for (User user : employees){
            userProfiles.add(user.getUserProfile());
        }
        model.addAttribute("employees", userProfiles);


        return "updateTask";
    }


    @PostMapping("/updateTask/{id}")
    public String updateTask(@PathVariable("id") int id,
                             @Valid
                             @ModelAttribute("ptuDto")
                             PTU_dto ptuDto,
                             BindingResult result) {

        ProjectTaskUser projectTU = projectTUService.findById(id)
                .orElseThrow();
        if (result.hasErrors()) {
            return "/updateTask";
        }

        projectTU.getTask().setNumber(ptuDto.getTaskNumber());
        projectTU.getTask().setName(ptuDto.getTaskName());
        projectTU.getTask().setStructure(ptuDto.getTaskStructure());
        projectTU.getTask().setCategory(ptuDto.getTaskCategory());
        projectTU.getTask().setStatus(ptuDto.getTaskStatus());
        projectTU.getTask().setComments(ptuDto.getComments());
        try {
            Date completionDate = dateFormat.parse(ptuDto.getCompletionDate());
            projectTU.getTask().setCompletionDate(completionDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User userFound = userService.findByUserProfile(ptuDto.getUserProfile())
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));
        projectTU.setUserProfile(userFound.getUserProfile());

        projectTUService.update(projectTU);


        return "redirect:/viewProject/" + projectTU.getProject().getId();
    }

}
