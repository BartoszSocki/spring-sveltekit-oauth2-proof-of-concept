package com.sockib.springresourceserver.model.dto.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import static com.sockib.springresourceserver.model.dto.input.InputValidationRegex.SUPPORTED_CURRENCIES;

@Getter
@Setter
public class MoneyInput {

    @NotNull(message = "amount is mandatory")
    @Positive(message = "amount must be greater than zero")
    private Double amount;

    @Size(max = 256, message = "currency name is too long")
    @NotBlank(message = "currency name cannot be empty")
    @NotNull(message = "currency name is mandatory")
    @Pattern(regexp = SUPPORTED_CURRENCIES)
    private String currency;

}
