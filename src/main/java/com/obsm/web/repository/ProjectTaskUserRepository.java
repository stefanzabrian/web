package com.obsm.web.repository;

import com.obsm.web.model.Project;
import com.obsm.web.model.ProjectTaskUser;
import com.obsm.web.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectTaskUserRepository extends JpaRepository<ProjectTaskUser, Integer> {
    List<ProjectTaskUser> findAllByProject(Project project);
    Optional<ProjectTaskUser> findByTask(Task task);
}
