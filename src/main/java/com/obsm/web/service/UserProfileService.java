package com.obsm.web.service;

import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.UserProfilePosition;

public interface UserProfileService {
    UserProfile save(UserProfile userProfile);
    UserProfile create (
            String firstName,
            String lastName,
            UserProfilePosition position,
            Double salary,
            String address
    );
}
