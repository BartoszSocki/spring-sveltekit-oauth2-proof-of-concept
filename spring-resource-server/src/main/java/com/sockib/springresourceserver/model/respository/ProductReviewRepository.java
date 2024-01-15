package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    @Query("select exists (select 1 from ProductReview pr where pr.reviewer.id = :userId and pr.product.id = :productId)")
    Boolean hasUserAddedReviewForProduct(Long userId, Long productId);

    @Query("select pr from ProductReview pr where pr.product.id =:productId and pr.reviewer.email = :email")
    Optional<ProductReview> findByProductIdAndOwnerEmail(Long productId, String email);

}