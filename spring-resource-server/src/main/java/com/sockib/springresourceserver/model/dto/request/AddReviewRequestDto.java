package com.sockib.springresourceserver.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class AddReviewRequestDto {

    @NotNull
    @Range(min = 1, max = 5)
    private Integer fiveStarScore;
    private String review;

}
