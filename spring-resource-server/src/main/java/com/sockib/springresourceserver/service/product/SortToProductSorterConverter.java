package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.Sort;
import com.sockib.springresourceserver.util.search.Sorter;

public interface SortToProductSorterConverter {

    Sorter<Product> convert(Sort sort);

}
