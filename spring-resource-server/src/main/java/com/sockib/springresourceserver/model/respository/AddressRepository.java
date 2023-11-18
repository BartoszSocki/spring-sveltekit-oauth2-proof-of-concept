package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.country = :country and a.city = :city and a.addressLine = :addressLine and a.postalCode = :postalCode and a.user.id = :userId")
    Optional<Address> findByAllFieldsAndUserId(String country, String city, String addressLine, String postalCode, Long userId);

}