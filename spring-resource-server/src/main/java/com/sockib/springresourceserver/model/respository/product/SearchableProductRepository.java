package com.sockib.springresourceserver.model.respository.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.sort.Sorter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SearchableProductRepository {

    List<Product> findProducts(Specification<Product> whereSpecification, Specification<Product> havingSpecification, Sorter<Product> sorter, Pageable pageable);

}
