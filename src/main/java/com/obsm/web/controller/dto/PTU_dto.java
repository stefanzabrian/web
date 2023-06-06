package com.obsm.web.controller.dto;

import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.TaskCategory;
import com.obsm.web.model.constant.TaskStatus;
import com.obsm.web.model.constant.TaskStructure;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PTU_dto {
    private int id;
    private Integer taskNumber;
    private String taskName;
    @Enumerated(EnumType.STRING)
    private TaskStructure taskStructure;
    @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    private String comments;
    private String completionDate;
    private UserProfile userProfile;

    public PTU_dto() {
    }

    public PTU_dto(Integer id,
                   Integer taskNumber,
                   String taskName,
                   TaskStructure taskStructure,
                   TaskCategory taskCategory,
                   TaskStatus taskStatus,
                   String comments,
                   String completionDate,
                   UserProfile userProfile) {
        this.id = id;
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.taskStructure = taskStructure;
        this.taskCategory = taskCategory;
        this.taskStatus = taskStatus;
        this.comments = comments;
        this.completionDate = completionDate;
        this.userProfile = userProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskStructure getTaskStructure() {
        return taskStructure;
    }

    public void setTaskStructure(TaskStructure taskStructure) {
        this.taskStructure = taskStructure;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
