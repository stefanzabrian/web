package com.obsm.web.service;

import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.User;
import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.UserRole;
import com.obsm.web.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(
            String email,
            String password,
            String phoneNumber,
            UserRole role,
            ClientProfile clientProfile
    ) {
        User user = new User(
                email,
                passwordEncoder.encode(password),
                phoneNumber,
                role

        );
        user.setClientProfile(clientProfile);

        userRepository.save(user);
    }

    @Override
    public void createAdmin(
            String email,
            String password,
            String phoneNumber,
            UserRole role,
            ClientProfile clientProfile,
            UserProfile userProfile
    ) {
        User user = new User(
                email,
                passwordEncoder.encode(password),
                phoneNumber,
                role
        );
        user.setClientProfile(clientProfile);
        user.setUserProfile(userProfile);

        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
