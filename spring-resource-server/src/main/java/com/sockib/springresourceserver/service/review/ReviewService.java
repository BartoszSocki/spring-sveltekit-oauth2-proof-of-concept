package com.sockib.springresourceserver.service.review;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;
import com.sockib.springresourceserver.model.entity.ProductReview;

import java.util.Optional;

public interface ReviewService {

    ProductReviewDto addReview(ReviewInputDto reviewInputDto, Long productId, String email);

    ProductReviewDto getProductReviewById(Long id);
}
