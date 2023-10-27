package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Sort {

    private String fieldName;
    private SortDirection sortDirection;

    public static Sort of(String fieldName, SortDirection sortDirection) {
        return Sort.builder()
                .fieldName(fieldName)
                .sortDirection(sortDirection)
                .build();
    }

}
