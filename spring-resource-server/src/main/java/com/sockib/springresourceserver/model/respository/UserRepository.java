package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}