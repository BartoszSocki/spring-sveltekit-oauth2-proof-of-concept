package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.exception.CurrencyNotSupportedException;
import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.value.ProductInventory;
import com.sockib.springresourceserver.model.value.Money;

public class ProductFactory {

    public static Product create(AddProductRequestDto dto) {
        if (!"USD".equals(dto.getPrice().getCurrency())) {
            throw new CurrencyNotSupportedException("Only USD is supported (for now)");
        }

        var product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .inventory(new ProductInventory(dto.getQuantity()))
                .price(Money.USD(dto.getPrice().getAmount()))
                .build();

        product.addTags(dto.getTags());
        product.addCategory(dto.getCategory());

        return product;
    }

}
