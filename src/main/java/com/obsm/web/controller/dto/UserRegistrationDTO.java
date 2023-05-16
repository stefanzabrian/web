package com.obsm.web.controller.dto;

import com.obsm.web.utils.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Password must match")
})
public class UserRegistrationDTO {
    @NotNull(message="* Complete the field")
    @NotBlank(message="* Complete the field")
    @Email
    private String email;
    @NotNull(message="* Complete the field")
    @NotBlank(message="* Complete the field")
    //@Pattern(regexp = "")
    private String password;
    @NotNull(message="* Complete the field")
    @NotBlank(message="* Complete the field")
    //@Pattern(regexp = "")
    private String confirmPassword;
    @NotNull(message="* Complete the field")
    @NotBlank(message="* Complete the field")
    //@Pattern(regexp = "")
    private String phoneNumber;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
