package com.sockib.springresourceserver.domain.product.query;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class ProductQueryCriteria {

    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer minScore;
    private Integer maxScore;
    private Set<String> tags;
    private String category;

    private ProductSortCriteria sortCriteria;

}
