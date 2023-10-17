package com.sockib.springresourceserver.services;

import com.sockib.springresourceserver.model.dto.ReviewDetails;
import com.sockib.springresourceserver.model.entity.ProductReview;
import com.sockib.springresourceserver.model.entity.UserReview;

import java.util.List;

public interface ReviewService {

    List<UserReview> getUserReviews(Long userId);
    List<ProductReview> getProductReview(Long productId);
    void addUserReview(Long userId, Long reviewerId, ReviewDetails reviewDetails);
    void addProductReview(Long productId, Long reviewerId, ReviewDetails reviewDetails);
    void changeReview(Long reviewId, Long reviewerId, ReviewDetails reviewDetails);
    void removeReview(Long reviewId, Long reviewerId);

}
