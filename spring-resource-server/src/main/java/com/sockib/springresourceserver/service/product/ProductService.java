package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.SearchFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable);
    List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort);

}
