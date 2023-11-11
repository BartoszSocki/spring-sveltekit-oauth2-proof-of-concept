package com.sockib.springresourceserver.model.dto.input;

import com.sockib.springresourceserver.util.search.filter.SearchOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static com.sockib.springresourceserver.model.dto.input.InputValidationRegex.WITHOUT_INVALID_CHARACTERS;

@Getter
@Setter
public class SearchInput {

    @NotBlank(message = "search filter field name cannot be empty")
    @NotNull(message = "search filter field name is mandatory")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "field name contains invalid characters")
    private String fieldName;

    private SearchOperation searchOperation;

    @NotBlank(message = "search filter field value cannot be empty")
    @NotNull(message = "search filter field value is mandatory")
    @Pattern(regexp = WITHOUT_INVALID_CHARACTERS, message = "field value contains invalid characters")
    private String fieldValue;

}
