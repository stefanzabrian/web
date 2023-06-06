package com.obsm.web.service;

import com.obsm.web.model.Project;
import com.obsm.web.model.ProjectTaskUser;
import com.obsm.web.model.Task;
import com.obsm.web.repository.ProjectTaskUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectTaskUserServiceImpl implements ProjectTaskUserService {
    private final ProjectTaskUserRepository projectTaskUserRepository;

    public ProjectTaskUserServiceImpl(ProjectTaskUserRepository projectTaskUserRepository) {
        this.projectTaskUserRepository = projectTaskUserRepository;
    }

    @Override
    public void save(ProjectTaskUser projectTaskUser) {
        projectTaskUserRepository.save(projectTaskUser);
    }

    @Override
    public void update(ProjectTaskUser projectTaskUser) {
        projectTaskUserRepository.saveAndFlush(projectTaskUser);
    }

    @Override
    public List<ProjectTaskUser> findAllByProject(Project project) {
        return projectTaskUserRepository.findAllByProject(project);
    }

    @Override
    public Optional<ProjectTaskUser> findByTask(Task task) {
        return projectTaskUserRepository.findByTask(task);
    }

    @Override
    public Optional<ProjectTaskUser> findById(int id) {
        return projectTaskUserRepository.findById(id);
    }
}
