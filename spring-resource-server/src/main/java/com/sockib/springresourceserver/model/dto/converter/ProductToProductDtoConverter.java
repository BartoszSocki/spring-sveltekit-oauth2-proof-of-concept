package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Tag;

public class ProductToProductDtoConverter implements ToDtoConverter<Product, ProductDto> {

    private static final MoneyToMoneyDtoConverter moneyConverter = new MoneyToMoneyDtoConverter();

    @Override
    public ProductDto convert(Product product) {
        var moneyDto = moneyConverter.convert(product.getPrice());

        var tags = product.getTags().stream().map(Tag::getName).toList();

        var productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setOwnerId(product.getOwner().getId());
        productDto.setName(product.getName());
        productDto.setPrice(moneyDto);
        productDto.setQuantity(product.getInventory().getQuantity());
        productDto.setCategory(product.getCategory().getName());
        productDto.setTags(tags);

        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setProductScore(product.getProductScore());

        return productDto;
    }
}
