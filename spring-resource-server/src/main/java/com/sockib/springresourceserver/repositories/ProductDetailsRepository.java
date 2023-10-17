package com.sockib.springresourceserver.repositories;

import com.sockib.springresourceserver.model.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

}