package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Category_;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.model.entity.Tag_;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.SearchOperation;
import com.sockib.springresourceserver.util.search.Specification;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

public class SearchFilterToProductSpecificationConverterImpl implements SearchFilterToProductSpecificationConverter {

    @Override
    public Specification<Product> convert(SearchFilter searchFilter) {
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

        if ("category".equals(field) && SearchOperation.EQ.equals(op)) {
            return withCategory(value);
        }

        if ("tag".equals(field) && SearchOperation.IN.equals(op)) {
            List<String> tags = Arrays.stream(value.split(" ")).toList();
            return withTags(tags);
        }

        throw new RuntimeException("combination of field name: " + field + " and operator: " + op + " not found");
    }

    @Override
    public Specification<Product> convert(List<SearchFilter> filters) {
        return filters.stream()
                .map(this::convert)
                .reduce(Specification::and)
                .orElseThrow(() -> new RuntimeException("TODO: add what went wrong"));
    }

    public Specification<Product> nameLike(String name) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(path.get(Product_.NAME)), "%" + name.toLowerCase() + "%");
    }

    public Specification<Product> priceGreaterThan(Integer value) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(path.get(Product_.PRICE).get("price"), max(0, value));
    }

    public Specification<Product> priceLessThan(Integer value) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(path.get(Product_.PRICE).get("price"), max(0, value));
    }

    private Specification<Product> withCategory(String name) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(path.get(Product_.CATEGORY).get(Category_.NAME), name);
    }

    private Specification<Product> withTags(List<String> tags) {
        return (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(path.get(Product_.TAGS).get(Tag_.NAME)).value(tags);
    }

}
