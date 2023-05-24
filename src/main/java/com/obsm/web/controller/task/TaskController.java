package com.obsm.web.controller.task;

import com.obsm.web.model.Project;
import com.obsm.web.model.Task;
import com.obsm.web.model.constant.TaskCategory;
import com.obsm.web.model.constant.TaskStatus;
import com.obsm.web.model.constant.TaskStructure;
import com.obsm.web.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/addTask")
    public String showAddTaskPage(Task task) {
        return "addTask";
    }

    @ModelAttribute("task")
    public Task getTask() {
        return new Task();
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

        taskService.create(task);

        return "redirect:/addTask?success";
    }

}
