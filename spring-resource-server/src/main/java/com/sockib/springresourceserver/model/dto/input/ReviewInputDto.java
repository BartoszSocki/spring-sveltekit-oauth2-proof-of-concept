package com.sockib.springresourceserver.model.dto.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ReviewInputDto {

    @Range(min = 1, max = 5)
    private Integer fiveStarScore;
    private String review;

}
