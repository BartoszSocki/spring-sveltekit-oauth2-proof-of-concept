package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSpecification;

import static com.sockib.springresourceserver.domain.product.query.ProductSpecifications.*;

public class ProductSpecificationFactory {

    public static ProductSpecification create(ProductQueryCriteria criteria) {
        return deleted(false)
                .and(priceBetween(criteria.getMinPrice(), criteria.getMaxPrice()))
                .and(nameLike(criteria.getName()))
                .and(category(criteria.getCategory()))
                .and(tags(criteria.getTags()))
                .and(scoreBetween(criteria.getMinScore(), criteria.getMaxScore()));
    }

}
