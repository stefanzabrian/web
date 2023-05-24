package com.obsm.web.service;

import com.obsm.web.model.ClientProfile;
import com.obsm.web.repository.ClientProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientProfileServiceImpl implements ClientProfileService {
    private final ClientProfileRepository clientProfileRepository;

    public ClientProfileServiceImpl(ClientProfileRepository clientProfileRepository) {
        this.clientProfileRepository = clientProfileRepository;
    }

    @Override
    public Optional<ClientProfile> getById(int id) {
        return clientProfileRepository.findById(id);
    }

    @Override
    public ClientProfile save(ClientProfile clientProfile) {
       return clientProfileRepository.save(clientProfile);
    }

    @Override
    public ClientProfile create(String firstName, String lastName, String address) {
        ClientProfile clientProfile = new ClientProfile(
                firstName,
                lastName,
                address
        );

        return clientProfileRepository.save(clientProfile);
    }
}
