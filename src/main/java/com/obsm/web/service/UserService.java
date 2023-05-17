package com.obsm.web.service;

import com.obsm.web.model.User;
import com.obsm.web.model.constant.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByEmail(String email);
    void create(
            String email,
            String password,
            String phoneNumber,
            UserRole role
    );
}
