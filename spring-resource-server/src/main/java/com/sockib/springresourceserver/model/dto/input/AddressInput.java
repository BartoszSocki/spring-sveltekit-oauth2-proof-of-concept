package com.sockib.springresourceserver.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static com.sockib.springresourceserver.model.dto.input.InputValidationRegex.POSTAL_CODE;
import static com.sockib.springresourceserver.model.dto.input.InputValidationRegex.WITHOUT_INVALID_CHARACTERS;

@Getter
@Setter
public class AddressInput {

    @NotBlank(message = "address line cannot be empty")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "address line contains invalid character")
    private String addressLine;

    @NotBlank(message = "country cannot be empty")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "country contains invalid character")
    private String country;

    @NotBlank(message = "city cannot be empty")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "city contains invalid character")
    private String city;

    @NotBlank(message = "postal code cannot be empty")
    @Pattern(regexp = POSTAL_CODE, message = "invalid postal code")
    private String postalCode;

}
