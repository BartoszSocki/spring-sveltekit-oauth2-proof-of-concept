package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtProductRepository extends JpaRepository<BoughtProduct, Long> {
}