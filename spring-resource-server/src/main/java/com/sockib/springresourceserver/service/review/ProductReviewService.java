package com.sockib.springresourceserver.service.review;

import com.sockib.springresourceserver.model.dto.response.ProductReviewResponseDto;
import com.sockib.springresourceserver.model.dto.request.AddReviewRequestDto;

public interface ProductReviewService {

    void putReview(AddReviewRequestDto addReviewRequestDto, Long productId, String email);

    ProductReviewResponseDto getProductReviewById(Long id);

    ProductReviewResponseDto getProductReviewByProductIdAndUserEmail(Long productId, String email);

}
