package com.obsm.web.service;

import com.obsm.web.model.Project;
import com.obsm.web.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(Project project) {
        projectRepository.save(project);
    }
}
