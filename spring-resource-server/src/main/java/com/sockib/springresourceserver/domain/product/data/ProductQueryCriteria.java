package com.sockib.springresourceserver.domain.product.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class ProductQueryCriteria {

    private Long ownerId;
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer minScore;
    private Integer maxScore;
    private Set<String> tags;
    private String category;

    private String sortField;
    private String sortDirection;

}
