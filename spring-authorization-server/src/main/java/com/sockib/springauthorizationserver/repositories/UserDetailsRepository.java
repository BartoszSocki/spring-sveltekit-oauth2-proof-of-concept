package com.sockib.springauthorizationserver.repositories;

import com.sockib.springauthorizationserver.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
