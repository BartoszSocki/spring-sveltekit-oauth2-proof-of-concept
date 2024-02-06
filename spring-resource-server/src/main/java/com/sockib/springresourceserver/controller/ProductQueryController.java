package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.domain.product.data.ProductQueryCriteria;
import com.sockib.springresourceserver.model.dto.response.ProductResponseDto;
import com.sockib.springresourceserver.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@RestController
public class ProductQueryController {

    private final ProductService productService;

    @GetMapping("/api/products")
    List<ProductResponseDto> queryProducts(ProductQueryCriteria criteria, Pageable pageable) {
        return productService.searchProduct(criteria, pageable);
    }

}
