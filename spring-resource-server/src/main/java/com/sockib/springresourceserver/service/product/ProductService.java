package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.model.dto.response.ProductResponseDto;
import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> searchProduct(ProductQueryCriteria productQueryCriteria, Pageable pageable);

    Product addNewProduct(AddProductRequestDto productInput);

    void deleteProduct(Long productId);

}
