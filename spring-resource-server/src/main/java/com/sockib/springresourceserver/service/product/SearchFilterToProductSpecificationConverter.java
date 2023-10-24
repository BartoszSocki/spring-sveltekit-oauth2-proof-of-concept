package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.Specification;

import java.util.List;

public interface SearchFilterToProductSpecificationConverter {

    Specification<Product> convert(SearchFilter searchFilter);
    Specification<Product> convert(List<SearchFilter> searchFilters);

}
