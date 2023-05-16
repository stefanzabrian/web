package com.obsm.web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_task_user")
public class ProjectTaskUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    public ProjectTaskUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task tasks) {
        this.task = tasks;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}