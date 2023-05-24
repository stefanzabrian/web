package com.obsm.web.service;

import com.obsm.web.model.Task;
import com.obsm.web.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void create(Task task) {
        taskRepository.save(task);
    }
}
