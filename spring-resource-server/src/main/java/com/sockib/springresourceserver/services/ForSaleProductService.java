package com.sockib.springresourceserver.services;

import com.sockib.springresourceserver.model.dto.ProductForSaleDetails;

public interface ForSaleProductService extends ProductSearchService {

    void addProductForSale(Long sellerId, ProductForSaleDetails productForSaleDetails);
    void changeProductForSaleDetails(Long sellerId, ProductForSaleDetails productForSaleDetails);
    void removeProductForSale(Long sellerId, Long productId);

}
