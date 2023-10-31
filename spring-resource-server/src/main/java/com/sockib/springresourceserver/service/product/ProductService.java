package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.model.dto.ProductInputDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.Page;
import com.sockib.springresourceserver.util.search.Pageable;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.Sort;

import java.util.List;

public interface ProductService {

    Page<ProductDto> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort);
    Product addNewProduct(ProductInputDto productInputDto, String email);

}
