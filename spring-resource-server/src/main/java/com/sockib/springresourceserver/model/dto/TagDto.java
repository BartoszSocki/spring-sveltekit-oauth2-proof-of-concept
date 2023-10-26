package com.sockib.springresourceserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {

    @Size(max = 32, message = "tag name is too long")
    @NotBlank(message = "tag name cannot be empty")
    @NotNull(message = "tag name is mandatory")
    @Pattern(regexp = "^[ \\w]+$")
    private String name;

}
