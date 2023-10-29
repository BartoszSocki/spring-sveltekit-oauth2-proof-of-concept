package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
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
