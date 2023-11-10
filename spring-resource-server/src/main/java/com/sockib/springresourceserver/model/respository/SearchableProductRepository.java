package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.*;

public interface SearchableProductRepository {

    SimplePage<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter, String entityGraph);
    SimplePage<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter);

}
