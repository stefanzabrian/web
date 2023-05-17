package com.obsm.web.controller.dto;

import com.obsm.web.model.constant.UserRole;
import jakarta.validation.constraints.NotNull;

public class UserDTO extends UserRegistrationDTO {
    @NotNull
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(
                   String email,
                   String password,
                   String confirmPassword,
                   String phoneNumber,
                   UserRole role
    ) {
        super(email,password,confirmPassword,phoneNumber);
        this.role = role;

    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
