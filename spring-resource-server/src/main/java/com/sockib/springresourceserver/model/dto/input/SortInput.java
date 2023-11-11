package com.sockib.springresourceserver.model.dto.input;

import com.sockib.springresourceserver.util.search.sort.SortDirection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortInput {

    @NotBlank(message = "sort field name cannot be empty")
    @NotNull(message = "sort field name is mandatory")
    @Pattern(regexp = "^\\w+$")
    private String fieldName;
    private SortDirection sortDirection;

}
