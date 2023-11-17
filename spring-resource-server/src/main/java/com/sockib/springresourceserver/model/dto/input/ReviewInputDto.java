package com.sockib.springresourceserver.model.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ReviewInputDto {

    @NotNull
    @Range(min = 1, max = 5)
    private Integer fiveStarScore;
    private String review;

}
