package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.entity.ProductReview;

public class ToProductReviewDtoConverter implements ToDtoConverter<ProductReview, ProductReviewDto> {

    @Override
    public ProductReviewDto convert(ProductReview productReview) {
        var productReviewDto = new ProductReviewDto();

        productReviewDto.setReviewerId(productReview.getReviewer().getId());
        productReviewDto.setId(productReview.getId());
        productReviewDto.setFiveStarScore(productReview.getFiveStarScore().getFiveStarScore());
        productReviewDto.setReview(productReview.getReview());

        return productReviewDto;
    }

}
