package com.obsm.web.repository;

import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByClientProfile(ClientProfile clientProfile);
    Optional<Order> findByIdAndClientProfile(int id, ClientProfile clientProfile);
}
