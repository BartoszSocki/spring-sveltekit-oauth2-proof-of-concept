package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SearchFilter {

    private String fieldName;
    private SearchOperation searchOperation;
    private String fieldValue;

}
