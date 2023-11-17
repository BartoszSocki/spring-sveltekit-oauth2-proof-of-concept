package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}