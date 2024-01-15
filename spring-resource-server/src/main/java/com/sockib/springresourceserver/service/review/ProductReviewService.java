package com.sockib.springresourceserver.service.review;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;

public interface ProductReviewService {

    void putReview(ReviewInputDto reviewInputDto, Long productId, String email);

    ProductReviewDto getProductReviewById(Long id);

    ProductReviewDto getProductReviewByProductIdAndUserEmail(Long productId, String email);

}
