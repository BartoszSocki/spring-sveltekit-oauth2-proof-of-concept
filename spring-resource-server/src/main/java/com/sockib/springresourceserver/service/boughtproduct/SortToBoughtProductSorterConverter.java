package com.sockib.springresourceserver.service.boughtproduct;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.model.entity.BoughtProduct_;
import com.sockib.springresourceserver.util.search.sort.Sort;
import com.sockib.springresourceserver.util.search.sort.SortDirection;
import com.sockib.springresourceserver.util.search.sort.Sorter;

public class SortToBoughtProductSorterConverter {

    public Sorter<BoughtProduct> convert(Sort sort) {
        var dir = sort.getSortDirection();
        return switch (sort.getFieldName()) {
            case "date" -> byDate(dir);
            default -> throw new RuntimeException("sorting by field " + sort.getFieldName() + " is not supported");
        };
    }

    private Sorter<BoughtProduct> byDate(SortDirection sortDirection) {
        return (path, criteriaQuery, criteriaBuilder) -> {
            var field = path.get(BoughtProduct_.CREATION_DATE);
            return sortDirection == SortDirection.ASC ? criteriaBuilder.asc(field) : criteriaBuilder.desc(field);
        };
    }

}
