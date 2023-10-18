package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.entity.ConcreteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcreteProductRepository extends JpaRepository<ConcreteProduct, Long> {
}