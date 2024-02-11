package com.sockib.springresourceserver.domain.product.factory;

import com.sockib.springresourceserver.exception.CurrencyNotSupportedException;
import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.entity.Category;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Tag;
import com.sockib.springresourceserver.model.respository.CategoryRepository;
import com.sockib.springresourceserver.model.respository.TagRepository;
import com.sockib.springresourceserver.model.value.Money;
import com.sockib.springresourceserver.model.value.ProductInventory;

import java.util.ArrayList;

public class ProductFactory {

    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    public ProductFactory(TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product create(AddProductRequestDto dto) {
        if (!"USD".equals(dto.getPrice().getCurrency())) {
            throw new CurrencyNotSupportedException("Only USD is supported (for now)");
        }

        var tags = dto.getTags().stream().map(tag -> {
            if (tagRepository.existsById(tag)) {
                return tagRepository.getReferenceById(tag);
            }
            return new Tag(tag);
        }).toList();

        var category = categoryRepository.existsById(dto.getCategory())
                ? categoryRepository.getReferenceById(dto.getCategory())
                : new Category(dto.getCategory());

        return Product.builder()
                .isDeleted(false)
                .name(dto.getName())
                .description(dto.getDescription())
                .category(category)
                .tags(tags)
                .inventory(new ProductInventory(dto.getQuantity()))
                .price(Money.USD(dto.getPrice().getAmount())).build();
    }

}
