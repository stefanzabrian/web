package com.obsm.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClientProfileDTO {
    private Integer id;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    private String firstName;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    private String lastName;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    private String address;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    private String phoneNumber;

    public ClientProfileDTO() {
    }

    public ClientProfileDTO(Integer id, String firstName, String lastName, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
