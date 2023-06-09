package com.obsm.web.model;

import com.obsm.web.model.constant.UserProfilePosition;
import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String fistName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private UserProfilePosition position;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "address")
    private String address;

    public UserProfile() {
    }

    public UserProfile(String fistName, String lastName, UserProfilePosition position, Double salary, String address) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserProfilePosition getPosition() {
        return position;
    }

    public void setPosition(UserProfilePosition position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
