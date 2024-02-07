package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSorter;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.sort.Sorter;

public class ProductSorterFactory {

    public static Sorter<Product> create(ProductQueryCriteria criteria) {
        boolean isAscending = criteria.getSortCriteria().isAscending();

        return switch (criteria.getSortCriteria().getSortField()) {
            case SCORE -> ProductSorter.score(isAscending);
            case PRICE -> ProductSorter.price(isAscending);
        };
    }

    public static Sorter<Product> noSort() {
        return ProductSorter.score(true);
    }

}
