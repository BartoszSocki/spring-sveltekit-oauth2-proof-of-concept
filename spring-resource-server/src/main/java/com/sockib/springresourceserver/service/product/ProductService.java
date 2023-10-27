package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductInputDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.Page;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.Sort;

import java.util.List;

public interface ProductService {

    List<Product> searchProduct(List<SearchFilter> filters, Page page);
    List<Product> searchProduct(List<SearchFilter> filters, Page page, Sort sort);
    Product addNewProduct(ProductInputDto productInputDto, String email);

}
