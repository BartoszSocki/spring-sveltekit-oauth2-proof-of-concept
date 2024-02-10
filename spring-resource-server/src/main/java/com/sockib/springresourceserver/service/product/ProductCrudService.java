package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.entity.Product;

public interface ProductCrudService {

    Product saveProduct(AddProductRequestDto productInput);

    void deleteProduct(Long productId);

}
