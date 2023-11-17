package com.sockib.springresourceserver.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReviewDto {

    private Long id;
    private Long reviewerId;
    private Integer fiveStarScore;
    private String review;

}
