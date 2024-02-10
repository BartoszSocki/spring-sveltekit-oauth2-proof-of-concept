package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSpecification;
import com.sockib.springresourceserver.domain.product.query.ProductSpecifications;

public class ProductSpecificationFactory {

    public static ProductSpecification create(ProductQueryCriteria criteria) {
        return ProductSpecifications.priceBetween(criteria.getMinPrice(), criteria.getMaxPrice())
                .and(ProductSpecifications.nameLike(criteria.getName()))
                .and(ProductSpecifications.category(criteria.getCategory()))
                .and(ProductSpecifications.tags(criteria.getTags()))
                .and(ProductSpecifications.scoreBetween(criteria.getMinScore(), criteria.getMaxScore()));
    }

}
