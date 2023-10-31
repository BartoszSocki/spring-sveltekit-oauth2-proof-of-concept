package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.service.product.ProductService;
import com.sockib.springresourceserver.util.search.Pageable;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.Sort;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor

@Controller
public class ProductController {

    private final ProductService productService;

//    @QueryMapping
//    List<ProductDto> searchProducts(@Argument List<SearchFilter> filters) {
//        var products = productService.searchProduct(filters, Page.of(0, 10)).stream()
//                .map(ProductDto::new)
//                .toList();
//
//        return products;
//    }

    @QueryMapping
    List<ProductDto> searchProducts(@Argument List<SearchFilter> filters, @Argument Pageable pageable, @Argument Sort sort) {
        List<ProductDto> list = productService.searchProduct(filters, pageable, sort).stream()
                .map(ProductDto::new)
                .toList();
        return list;
    }

}
