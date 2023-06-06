package com.obsm.web.repository;

import com.obsm.web.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {
}
