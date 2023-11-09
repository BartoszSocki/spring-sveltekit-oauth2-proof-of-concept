package com.sockib.springresourceserver.model.dto.input;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableInput {

    @PositiveOrZero(message = "offset cannot be less than zero")
    private Long offset;

    @Positive(message = "limit cannot be less than or equal to zero")
    private Long limit;

}
