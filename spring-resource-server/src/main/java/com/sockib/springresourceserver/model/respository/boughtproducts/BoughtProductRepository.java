package com.sockib.springresourceserver.model.respository.boughtproducts;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoughtProductRepository extends JpaRepository<BoughtProduct, Long>, SearchableBoughtProductRepository {

//    Boolean existsBoughtProductByProductIdAndOwnerId(Long productId, Long ownerId);
    @Query("select exists (select 1 from BoughtProduct bp where bp.owner.id = :userId and bp.product.id = :productId)")
    Boolean isUserOwnerOfProduct(Long userId, Long productId);

}