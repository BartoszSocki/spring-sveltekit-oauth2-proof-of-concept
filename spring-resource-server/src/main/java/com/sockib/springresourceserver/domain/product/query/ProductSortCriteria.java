package com.sockib.springresourceserver.domain.product.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSortCriteria {

    private Field sortField;
    private SortDirection sortDirection;

    private ProductSortCriteria(Field sortField, SortDirection sortDirection) {
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }

    public boolean isAscending() {
        return SortDirection.ASC.equals(sortDirection);
    }

    public static ProductSortCriteria asc(Field field) {
        return new ProductSortCriteria(field, SortDirection.ASC);
    }

    public static ProductSortCriteria desc(Field field) {
        return new ProductSortCriteria(field, SortDirection.DESC);
    }

    public enum Field {
        PRICE, SCORE
    }

    public enum SortDirection {
        ASC, DESC
    }

}
