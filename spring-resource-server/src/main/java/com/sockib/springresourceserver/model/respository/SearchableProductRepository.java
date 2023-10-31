package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.Page;
import com.sockib.springresourceserver.util.search.Pageable;
import com.sockib.springresourceserver.util.search.Sort;
import com.sockib.springresourceserver.util.search.Specification;

import java.util.List;

public interface SearchableProductRepository {

    Page<Product> findProducts(Specification<Product> specification, Pageable pageable, Sort sort, String entityGraphName);
    Page<Product> findProducts(Specification<Product> specification, Pageable pageable, Sort sort);

}
