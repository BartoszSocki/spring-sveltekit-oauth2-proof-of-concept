package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.core.util.DtoConverter;
import com.sockib.springresourceserver.model.dto.response.ProductReviewResponseDto;
import com.sockib.springresourceserver.model.entity.ProductReview;

public class ProductReviewDtoConverter implements DtoConverter<ProductReview, ProductReviewResponseDto> {

    @Override
    public ProductReviewResponseDto convert(ProductReview productReview) {
        var productReviewDto = new ProductReviewResponseDto();

        productReviewDto.setReviewerId(productReview.getReviewer().getId());
        productReviewDto.setId(productReview.getId());
        productReviewDto.setFiveStarScore(productReview.getFiveStarScore().getFiveStarScore());
        productReviewDto.setReview(productReview.getReview());

        return productReviewDto;
    }

}
