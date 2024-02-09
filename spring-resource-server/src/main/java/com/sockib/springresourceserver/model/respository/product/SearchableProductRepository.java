package com.sockib.springresourceserver.model.respository.product;

import com.sockib.springresourceserver.domain.product.query.ProductSpecification;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.core.util.Sorter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchableProductRepository {

    List<Product> findProducts(ProductSpecification specification, Sorter<Product> sorter, Pageable pageable);

}
