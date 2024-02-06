package com.sockib.springresourceserver.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReviewResponseDto {

    private Long id;
    private Long reviewerId;
    private Integer fiveStarScore;
    private String review;

}
