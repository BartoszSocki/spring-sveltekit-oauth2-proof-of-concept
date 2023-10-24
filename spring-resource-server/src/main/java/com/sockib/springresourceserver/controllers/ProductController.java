package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.service.product.ProductService;
import com.sockib.springresourceserver.util.search.SearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor

@Controller
public class ProductController {

    private final ProductService productService;

    @QueryMapping
    List<Product> searchProducts(@Argument List<SearchFilter> filters) {
        return productService.searchProduct(filters, Pageable.ofSize(10));
    }

}
