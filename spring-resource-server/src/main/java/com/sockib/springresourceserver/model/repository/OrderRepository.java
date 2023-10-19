package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
