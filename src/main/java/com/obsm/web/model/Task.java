package com.obsm.web.model;

import com.obsm.web.model.constant.TaskCategory;
import com.obsm.web.model.constant.TaskStatus;
import com.obsm.web.model.constant.TaskStructure;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "* Complete the field")
    @Column(name = "number")
    private Integer number;
    @NotNull(message = "* Complete the field")
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "structure")
    private TaskStructure structure;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private TaskCategory category;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
    @Column(name = "comments")
    private String comments;
    @Column(name = "completion_date")
    private Date completionDate;

    public Task() {
    }

    public Task(String name, Integer number, TaskStructure structure, TaskCategory category, TaskStatus status, String comments, Date completionDate) {
        this.name = name;
        this.number = number;
        this.structure = structure;
        this.category = category;
        this.status = status;
        this.comments = comments;
        this.completionDate = completionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStructure getStructure() {
        return structure;
    }

    public void setStructure(TaskStructure structure) {
        this.structure = structure;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

