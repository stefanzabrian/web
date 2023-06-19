package com.obsm.web.service;

import com.obsm.web.model.Project;
import com.obsm.web.model.Task;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void create(Project project);
    List<Project> findAll();
    Optional<Project> findById(int id);
}
