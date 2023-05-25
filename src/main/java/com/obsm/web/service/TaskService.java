package com.obsm.web.service;

import com.obsm.web.model.Task;

import java.util.List;

public interface TaskService {
    void create(Task task);
    List<Task> findAll();
}
