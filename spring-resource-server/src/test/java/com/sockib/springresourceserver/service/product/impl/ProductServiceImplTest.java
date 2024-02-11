package com.sockib.springresourceserver.service.product.impl;

import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.dto.request.MoneyRequestDto;
import com.sockib.springresourceserver.model.entity.Tag;
import com.sockib.springresourceserver.service.product.ProductCrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductCrudService productCrudService;

    @Test
    void givenProductDto_whenSaving_thenSuccess() {
        // given
        final String name = "name";
        final String category = "category";
        final int quantity = 10;
        final Set<String> tags = Set.of("Bio", "tag");
        MoneyRequestDto moneyDto = new MoneyRequestDto();
        moneyDto.setAmount(10.0);
        moneyDto.setCurrency("USD");

        AddProductRequestDto productDto = new AddProductRequestDto();
        productDto.setName(name);
        productDto.setCategory(category);
        productDto.setPrice(moneyDto);
        productDto.setQuantity(quantity);
        productDto.setTags(tags.stream().toList());

        // when
        // check if there is a constraint violation for tags or category
        productCrudService.saveProduct(productDto);
        var persistedProduct = productCrudService.saveProduct(productDto);

        // then
        Assertions.assertNotNull(persistedProduct);
        assertEquals(name, persistedProduct.getName());
        assertEquals(category, persistedProduct.getCategory().getName());
        assertEquals(quantity, persistedProduct.getInventory().getQuantity());
        assertEquals(tags, persistedProduct.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
    }

}