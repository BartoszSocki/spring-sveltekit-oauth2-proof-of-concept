package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.model.dto.response.ProductResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryService {

    List<ProductResponseDto> searchProduct(ProductQueryCriteria productQueryCriteria, Pageable pageable);

}
