package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.core.util.DtoConverter;
import com.sockib.springresourceserver.model.dto.response.ProductResponseDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Tag;

public class ProductDtoConverter implements DtoConverter<Product, ProductResponseDto> {

    private static final MoneyDtoConverter moneyConverter = new MoneyDtoConverter();

    @Override
    public ProductResponseDto convert(Product product) {
        var moneyDto = moneyConverter.convert(product.getPrice());

        var tags = product.getTags().stream().map(Tag::getName).toList();

        var productDto = new ProductResponseDto();
        productDto.setId(product.getId());
        productDto.setIsDeleted(product.getIsDeleted());
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
