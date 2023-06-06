package com.obsm.web.service;

import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.User;
import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);
    Optional<User> findByEmail(String email);
    User getByEmail(String email);
    List<User> findAll();
    Optional<User> findById(int id);
    void create(
            String email,
            String password,
            String phoneNumber,
            UserRole role,
            ClientProfile clientProfile
    );

    void createAdmin (
            String email,
            String password,
            String phoneNumber,
            UserRole role,
            ClientProfile clientProfile,
            UserProfile userProfile
    );
    List<User> findAllByUserRole(UserRole role);
    Optional<User> findByUserProfile(UserProfile userProfile);
}
