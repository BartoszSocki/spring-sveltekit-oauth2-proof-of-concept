package com.sockib.springresourceserver.model.respository.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.filter.Specification;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.sort.Sorter;

public interface SearchableProductRepository {

    SimplePage<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter, String entityGraph);
    SimplePage<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter);

}
