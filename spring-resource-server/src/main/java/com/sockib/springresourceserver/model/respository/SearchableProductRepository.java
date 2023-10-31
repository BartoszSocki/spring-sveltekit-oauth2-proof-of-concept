package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.*;

import java.util.List;

public interface SearchableProductRepository {

    Page<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter, String entityGraphName);
    Page<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter);

}
