package com.obsm.web.repository;

import com.obsm.web.model.User;
import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    Optional<User> findById(int id);
    List<User> findAllByRole(UserRole role);
    Optional<User> findByUserProfile(UserProfile userProfile);
}
