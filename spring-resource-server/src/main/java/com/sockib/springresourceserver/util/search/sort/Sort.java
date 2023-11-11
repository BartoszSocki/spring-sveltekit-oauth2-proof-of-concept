package com.sockib.springresourceserver.util.search.sort;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
