package com.sockib.springresourceserver.service.product;

//import com.sockib.springresourceserver.model.embeddable.Price_;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.SearchOperation;
import com.sockib.springresourceserver.util.search.Specification;

import java.util.List;

import static java.lang.Math.max;

public class ProductSearch {

    public static Specification<Product> resolve(SearchFilter searchFilter) {
        var field = searchFilter.getFieldName();
        var op = searchFilter.getSearchOperation();
        var value = searchFilter.getFieldValue();

        if ("name".equals(field) && SearchOperation.LIKE.equals(op)) {
            return nameLike(value);
        }

        if ("price".equals(field) && SearchOperation.GT.equals(op)) {
            return priceGreaterThan(Integer.valueOf(value));
        }

        if ("price".equals(field) && SearchOperation.LT.equals(op)) {
            return priceLessThan(Integer.valueOf(value));
        }

        throw new RuntimeException("combination of field name: " + field + " and operator: " + op + " not found");
    }

    public static Specification<Product> resolve(List<SearchFilter> filters) {
        return filters.stream()
                .map(ProductSearch::resolve)
                .reduce(Specification::and)
                .orElseThrow(() -> new RuntimeException("TODO: add what went wrong"));
    }

    public static Specification<Product> nameLike(String name) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(path.get(Product_.NAME)), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> priceGreaterThan(Integer value) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(path.get(Product_.PRICE).get("price"), max(0, value));
    }

    public static Specification<Product> priceLessThan(Integer value) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(path.get(Product_.PRICE).get("price"), max(0, value));
    }

}
