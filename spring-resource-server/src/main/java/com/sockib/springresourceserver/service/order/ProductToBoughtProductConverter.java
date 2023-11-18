package com.sockib.springresourceserver.service.order;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.model.entity.Product;

public class ProductToBoughtProductConverter {

    public BoughtProduct convert(Product product) {
        var boughtProduct = new BoughtProduct();
        boughtProduct.setName(product.getName());
        boughtProduct.setDescription(product.getDescription());
        boughtProduct.setCategory(product.getCategory());
        boughtProduct.setPrice(product.getPrice());
        boughtProduct.setImageUrl(product.getImageUrl());
        boughtProduct.setProduct(product);

        return boughtProduct;
    }
}
