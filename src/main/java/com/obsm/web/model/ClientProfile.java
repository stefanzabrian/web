package com.obsm.web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "client_profile")
public class ClientProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "Enter your name")
    @NotBlank(message = "Enter your name")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "Enter your name")
    @NotBlank(message = "Enter your name")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "Enter your address")
    @NotBlank(message = "Enter your address")
    @Column(name = "address")
    private String address;

    public ClientProfile() {
    }

    public ClientProfile(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
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
}
