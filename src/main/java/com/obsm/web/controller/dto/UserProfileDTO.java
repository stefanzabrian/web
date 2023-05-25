package com.obsm.web.controller.dto;

import com.obsm.web.model.constant.UserProfilePosition;
import com.obsm.web.model.constant.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserProfileDTO {
    private Integer id;
    private String email;
    @NotNull(message = "* Complete the field")
    private String firstName;
    @NotNull(message = "* Complete the field")
    private String lastName;
    private UserProfilePosition position;
    private Double salary;
    private String address;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    private String phoneNumber;

    public UserProfileDTO() {
    }

    public UserProfileDTO(Integer id,
                          String email,
                          String firstName,
                          String lastName,
                          UserProfilePosition position,
                          Double salary,
                          String address,
                          String phoneNumber
    ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
