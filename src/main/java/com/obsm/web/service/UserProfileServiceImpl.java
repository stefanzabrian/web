package com.obsm.web.service;

import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.UserProfilePosition;
import com.obsm.web.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
       return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile create(String firstName,
                              String lastName,
                              UserProfilePosition position,
                              Double salary,
                              String address
    ) {
        UserProfile userProfile = new UserProfile(
                firstName,
                lastName,
                position,
                salary,
                address
        );

        return userProfileRepository.save(userProfile);
    }
}
