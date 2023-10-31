package com.sockib.springresourceserver.util.search;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Sort {

    @NotBlank(message = "sort field name cannot be empty")
    @NotNull(message = "sort field name is mandatory")
    @Pattern(regexp = "^\\w+$")
    private String fieldName;
    private SortDirection sortDirection;

    public static Sort of(String fieldName, SortDirection sortDirection) {
        return Sort.builder()
                .fieldName(fieldName)
                .sortDirection(sortDirection)
                .build();
    }

}
