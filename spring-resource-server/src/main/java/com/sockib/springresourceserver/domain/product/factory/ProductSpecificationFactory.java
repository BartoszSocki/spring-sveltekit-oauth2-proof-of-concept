package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSpecification;
import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationFactory {

    public static Specification<Product> where(ProductQueryCriteria criteria) {
        return ProductSpecification.priceBetween(criteria.getMinPrice(), criteria.getMaxPrice())
                .and(ProductSpecification.nameLike(criteria.getName()));
    }

    public static Specification<Product> having(ProductQueryCriteria criteria) {
        return ProductSpecification.scoreBetween(criteria.getMinScore(), criteria.getMaxScore());
    }

}
