package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}