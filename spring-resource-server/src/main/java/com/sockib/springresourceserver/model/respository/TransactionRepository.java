package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Order, Long> {
}