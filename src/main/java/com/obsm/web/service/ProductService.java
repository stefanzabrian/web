package com.obsm.web.service;

import com.obsm.web.model.Product;

import java.util.Optional;

public interface ProductService  {
    void save(Product product);

    Optional<Product> findById(int id);
}
