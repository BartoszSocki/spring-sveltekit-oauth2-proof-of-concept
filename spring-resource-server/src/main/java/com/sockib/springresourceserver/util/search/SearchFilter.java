package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchFilter {

    private String fieldName;
    private SearchOperation searchOperation;
    private String fieldValue;

}
