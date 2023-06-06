package com.obsm.web.service;

import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllByUserEmail(String email);
    List<Order> findAll();
    Optional<Order> findByIdAndClientProfile(int id, ClientProfile clientProfile);
    Optional<Order> findById(int id);
    void delete(Order order);
    void update(Order order);
}
