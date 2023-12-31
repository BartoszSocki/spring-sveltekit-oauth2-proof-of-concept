package com.sockib.springresourceserver.util.search.filter;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchFilter {

    private String fieldName;
    private SearchOperation searchOperation;
    private String fieldValue;

}
