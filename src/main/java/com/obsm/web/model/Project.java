package com.obsm.web.model;

import com.obsm.web.model.constant.ProjectCategory;
import com.obsm.web.model.constant.ProjectPriority;
import com.obsm.web.model.constant.ProjectStatus;
import com.obsm.web.model.constant.ProjectType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    @Column(name = "name")
    private String name;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProjectType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProjectCategory category;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private ProjectPriority priority;
    @NotNull(message = "* Complete the field")
    @Column(name = "price")
    private Double price;
    @NotNull(message = "* Complete the field")
    @Column(name = "start_date")
    private Date startDate;
    @NotNull(message = "* Complete the field")
    @Column(name = "end_date")
    private Date endDate;

    public Project() {
    }

    public Project(String name, String description, ProjectType type, ProjectCategory category, ProjectStatus status, ProjectPriority priority, Double price, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public ProjectPriority getPriority() {
        return priority;
    }

    public void setPriority(ProjectPriority priority) {
        this.priority = priority;
    }
}
