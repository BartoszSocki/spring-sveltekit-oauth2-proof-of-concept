package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSortCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSorter;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.core.util.Sorter;

import java.util.Optional;

public class ProductSorterFactory {

    public static Sorter<Product> create(ProductQueryCriteria criteria) {
        ProductSortCriteria sortCriteria = Optional.ofNullable(criteria.getSortCriteria())
                .orElse(ProductSortCriteria.asc(ProductSortCriteria.Field.PRICE));

        return switch (sortCriteria.getSortField()) {
            case SCORE -> ProductSorter.score(sortCriteria.isAscending());
            case PRICE -> ProductSorter.price(sortCriteria.isAscending());
        };
    }

    public static Sorter<Product> noSort() {
        return ProductSorter.score(true);
    }

}
