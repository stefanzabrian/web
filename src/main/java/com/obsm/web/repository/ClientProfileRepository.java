package com.obsm.web.repository;

import com.obsm.web.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Integer> {
}
