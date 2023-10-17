package com.sockib.springresourceserver.services;

import com.sockib.springresourceserver.model.dto.ProductDTO;
import java.util.List;

public interface ProductSearchService {

    // TODO: add more methods
    List<ProductDTO> getProducts(Long sellerId);

}
