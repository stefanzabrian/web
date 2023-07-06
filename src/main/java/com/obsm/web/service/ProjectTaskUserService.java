package com.obsm.web.service;

import com.obsm.web.model.Project;
import com.obsm.web.model.ProjectTaskUser;
import com.obsm.web.model.Task;

import java.util.List;
import java.util.Optional;

public interface ProjectTaskUserService {
    void save(ProjectTaskUser projectTaskUser);
    void update(ProjectTaskUser projectTaskUser);
    List<ProjectTaskUser> findAllByProject(Project project);
    Optional<ProjectTaskUser> findByTask(Task task);
    Optional<ProjectTaskUser> findById(int id);
    void deleteTaskUser(ProjectTaskUser projectTaskUser);


}
