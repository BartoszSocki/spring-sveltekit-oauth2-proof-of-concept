package com.sockib.springresourceserver.model.dto.input;

import com.sockib.springresourceserver.model.dto.TagDto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductInput {

    @Size(max = 256, message = "product name is too long")
    @NotBlank(message = "product name cannot be empty")
    @NotNull(message = "product name is mandatory")
    @Pattern(regexp = "^[\\s\\w]+$")
    private String name;

    private MoneyInput price;

    @Positive(message = "product quantity must be non negative")
    @NotNull(message = "product quantity is mandatory")
    private Integer quantity;

    @Size(max = 256, message = "product category is too long")
    @NotBlank(message = "product category cannot be empty")
    @NotNull(message = "product category is mandatory")
    @Pattern(regexp = "^[\\s\\w]+$")
    private String category;

    @Size(max = 10, message = "too many tags")
    private List<TagDto> tags;

    @Size(max = 1024, message = "product description is too long")
    @Pattern(regexp = "^[\\s\\w]+$")
    private String description;

    // TODO: add validation
    private String imageUrl;

}
