package com.sockib.springresourceserver.model.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductInput {

    @NotNull(message = "id is mandatory")
    @PositiveOrZero(message = "id cannot be negative")
    private Long productId;

    @NotNull(message = "quantity is mandatory")
    @Positive(message = "quantity must be greater than zero")
    private Integer productQuantity;

}
