package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.util.search.SearchOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInput {

    @NotBlank(message = "search filter field name cannot be empty")
    @NotNull(message = "search filter field name is mandatory")
    @Pattern(regexp = "^[\\w\\s]+$")
    private String fieldName;

    private SearchOperation searchOperation;

    @NotBlank(message = "search filter field value cannot be empty")
    @NotNull(message = "search filter field value is mandatory")
    @Pattern(regexp = "^[-,\\w\\s]+$")
    private String fieldValue;

}
