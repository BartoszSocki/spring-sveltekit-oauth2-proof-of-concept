package com.sockib.springauthorizationserver.repository;

import com.sockib.springauthorizationserver.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select u from UserAccount u where u.user.email = :email")
    Optional<UserAccount> findByEmail(String email);

}
