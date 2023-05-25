package com.obsm.web.service;

import com.obsm.web.model.Project;

import java.util.List;

public interface ProjectService {
    void create(Project project);
    List<Project> findAll();
}
