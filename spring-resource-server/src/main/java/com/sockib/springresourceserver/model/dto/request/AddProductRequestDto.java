package com.sockib.springresourceserver.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.sockib.springresourceserver.model.dto.validation.RequestValidation.WITHOUT_INVALID_CHARACTERS;

@Getter
@Setter
public class AddProductRequestDto {

    @Size(max = 256, message = "product name is too long")
    @NotBlank(message = "product name cannot be empty")
    @NotNull(message = "product name is mandatory")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "name contains invalid characters")
    private String name;

    private MoneyRequestDto price;

    @Positive(message = "product quantity must be non negative")
    @NotNull(message = "product quantity is mandatory")
    private Integer quantity;

    @Size(max = 256, message = "product category is too long")
    @NotBlank(message = "product category cannot be empty")
    @NotNull(message = "product category is mandatory")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "category contains invalid characters")
    private String category;

    @Size(max = 10, message = "too many tags")
//    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "tags contains invalid characters")
    private List<String> tags;

    @Size(max = 1024, message = "product description is too long")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "description contains invalid characters")
    private String description;

    // TODO: add validation
    private String imageUrl;

}
