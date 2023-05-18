package com.obsm.web.service;

import com.obsm.web.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(Product product);

    Optional<Product> findById(int id);

    List<Product> findAll();
    void deleteById(int id);

}
