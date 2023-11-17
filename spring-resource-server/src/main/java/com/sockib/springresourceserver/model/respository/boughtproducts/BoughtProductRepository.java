package com.sockib.springresourceserver.model.respository.boughtproducts;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtProductRepository extends JpaRepository<BoughtProduct, Long>, SearchableBoughtProductRepository {

    Boolean existsBoughtProductByProductIdAndOwnerId(Long productId, Long ownerId);

}