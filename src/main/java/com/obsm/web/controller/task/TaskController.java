package com.obsm.web.controller.task;

import com.obsm.web.model.*;
import com.obsm.web.model.constant.*;
import com.obsm.web.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final ProjectTaskUserService projectTaskUserService;
    private final UserService userService;
    private Project projectFound = new Project();

    public TaskController(TaskService taskService,
                          ProjectService projectService,
                          ProjectTaskUserService projectTaskUserService,
                          UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.projectTaskUserService = projectTaskUserService;
        this.userService = userService;
    }

    // Task Related Controllers

    @GetMapping("/addTask")
    public String showAddTaskPage(Task task) {
        return "addTask";
    }


    @ModelAttribute("categories")
    public TaskCategory[] getTaskCategories() {
        return TaskCategory.values();
    }

    @ModelAttribute("statuses")
    public TaskStatus[] getTaskStatuses() {
        return TaskStatus.values();
    }

    @ModelAttribute("structures")
    public TaskStructure[] getTaskStructures() {
        return TaskStructure.values();
    }

    @PostMapping("/addTask")
    public String addProduct(
            @ModelAttribute("task")
            @Valid
            Task task,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "/addTask";

        }

        taskService.save(task);

        return "redirect:/addTask?success";
    }

    @GetMapping("/viewAllTasks")
    public String showViewAllTasksPage(Model model) {
        List<Task> tasks = taskService.findAll();

        model.addAttribute("tasks", tasks);

        return "viewAllTasks";
    }

}
