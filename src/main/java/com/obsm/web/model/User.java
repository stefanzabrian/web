package com.obsm.web.model;

import com.obsm.web.model.constant.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    @Email
    @Column(name = "email", unique = true)
    private String email;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    @Column(name = "password")
    private String password;
    @NotNull(message = "* Complete the field")
    @NotBlank(message = "* Complete the field")
    @Column(name = "phone_number")
    //@Pattern(ragexp = "")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
    @OneToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
    @OneToOne
    @JoinColumn(name = "client_profile_id")
    private ClientProfile clientProfile;

    public User() {
    }

    public User(String email, String password, String phoneNumber, UserRole role) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public ClientProfile getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }
}

