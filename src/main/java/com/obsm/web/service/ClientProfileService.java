package com.obsm.web.service;

import com.obsm.web.model.ClientProfile;

import java.util.Optional;

public interface ClientProfileService {
    Optional<ClientProfile> getById(int id);
    ClientProfile save(ClientProfile clientProfile);
    ClientProfile create(
            String firstName,
            String lastName,
            String address
    );
}
