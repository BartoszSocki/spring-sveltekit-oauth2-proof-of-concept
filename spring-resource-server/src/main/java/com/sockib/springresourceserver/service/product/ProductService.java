package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.model.dto.input.ProductInput;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.filter.SearchFilter;
import com.sockib.springresourceserver.util.search.sort.Sort;

import java.util.List;

public interface ProductService {

    SimplePage<ProductDto> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort);
    List<ProductDto> findProductsByIds(List<Long> ids);
    Product addNewProduct(ProductInput productInput, String email);

}
