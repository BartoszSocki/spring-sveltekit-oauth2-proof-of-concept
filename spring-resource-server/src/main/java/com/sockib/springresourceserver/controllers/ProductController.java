package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.*;
import com.sockib.springresourceserver.model.dto.input.PageableInput;
import com.sockib.springresourceserver.model.dto.input.ProductInput;
import com.sockib.springresourceserver.model.dto.input.SearchInput;
import com.sockib.springresourceserver.model.dto.input.SortInput;
import com.sockib.springresourceserver.service.product.ProductService;
import com.sockib.springresourceserver.util.search.Pageable;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.Sort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor

@Controller
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @QueryMapping
    PageDto<ProductDto> searchProducts(@Argument @Valid List<SearchInput> filters,
                                       @Argument @Valid PageableInput pageable,
                                       @Argument @Valid SortInput sort) {
        var productFilters = filters.stream().map(f -> modelMapper.map(f, SearchFilter.class)).toList();
        var productPageable = modelMapper.map(pageable, Pageable.class);
        var productSort = modelMapper.map(sort, Sort.class);

        // TODO: try to use mapper
        return new PageDto<>(productService.searchProduct(productFilters, productPageable, productSort));
    }

    @QueryMapping
    List<ProductDto> findProductsByIds(@Argument List<Long> ids) {
        return productService.findProductsByIds(ids);
    }

    @MutationMapping
    Boolean addProduct(@Argument @Valid ProductInput product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        productService.addNewProduct(product, email);
        return true;
    }
}
