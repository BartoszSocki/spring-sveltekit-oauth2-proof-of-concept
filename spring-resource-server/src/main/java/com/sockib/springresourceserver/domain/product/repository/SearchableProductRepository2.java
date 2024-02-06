package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SearchableProductRepository2 {

    List<Product> findProducts(Specification<Product> whereSpecification, Specification<Product> havingSpecification, Pageable pageable);

}
