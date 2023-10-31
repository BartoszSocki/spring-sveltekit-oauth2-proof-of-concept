package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.util.search.Sort;
import com.sockib.springresourceserver.util.search.SortDirection;
import com.sockib.springresourceserver.util.search.Sorter;

public class SortToProductSorterConverterImpl implements SortToProductSorterConverter {
    @Override
    public Sorter<Product> convert(Sort sort) {
        var dir = sort.getSortDirection();
        return switch (sort.getFieldName()) {
            case "name" -> byName(dir);
            case "price" -> byPrice(dir);
            case "score" -> byScore(dir);
            default -> throw new RuntimeException("sorting by field " + sort.getFieldName() + " is not supported");
        };
    }

    public Sorter<Product> byName(SortDirection sortDirection) {
        return (path, criteriaQuery, criteriaBuilder) -> {
            var field = criteriaBuilder.lower(path.get(Product_.NAME));
            return sortDirection == SortDirection.ASC ? criteriaBuilder.asc(field) : criteriaBuilder.desc(field);
        };
    }

    public Sorter<Product> byPrice(SortDirection sortDirection) {
        return (path, criteriaQuery, criteriaBuilder) -> {
            var field = path.get(Product_.PRICE).get("amount");
            return sortDirection == SortDirection.ASC ? criteriaBuilder.asc(field) : criteriaBuilder.desc(field);
        };
    }

    public Sorter<Product> byScore(SortDirection sortDirection) {
        return (path, criteriaQuery, criteriaBuilder) -> {
            var field = criteriaBuilder.literal(2);
            return sortDirection == SortDirection.ASC ? criteriaBuilder.asc(field) : criteriaBuilder.desc(field);
        };
    }

}
