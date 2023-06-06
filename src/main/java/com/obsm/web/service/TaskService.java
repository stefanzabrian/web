package com.obsm.web.service;

import com.obsm.web.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(int id);
}
