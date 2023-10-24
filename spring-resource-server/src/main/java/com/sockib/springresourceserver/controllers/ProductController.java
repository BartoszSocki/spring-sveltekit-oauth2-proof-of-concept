package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.util.search.SearchFilter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {

    @QueryMapping
    List<ProductDto> searchProducts(@Argument List<SearchFilter> filters) {
        return null;
    }

}
