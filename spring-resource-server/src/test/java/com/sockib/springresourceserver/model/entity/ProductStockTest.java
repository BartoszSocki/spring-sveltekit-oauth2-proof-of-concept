package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.repository.ProductDetailsRepository;
import com.sockib.springresourceserver.model.repository.ProductStockRepository;
import com.sockib.springresourceserver.model.valueobject.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductStockTest {

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    ProductDetails productDetails;
    final static Price PRICE = new Price(120D, "PLN");
    final static String DESCRIPTION = "desc";

    User seller;
    final static String SELLER_EMAIL = "email.com";

    final static String NAME = "name";

    @BeforeEach
    void init() {
        productDetails = new ProductDetails();
        productDetails.setDescription(DESCRIPTION);
        productDetails.setPrice(PRICE);
        productDetails.setName(NAME);

        seller = new User();
        seller.setEmail(SELLER_EMAIL);
    }

    @Test
    void givenProduct_whenPersist_thenSuccess() {
        // given
        final Integer quantity = 100;

        var product = new ProductStock();
        product.setQuantity(quantity);
        product.setProductDetails(productDetails);
        product.setSeller(seller);

        // when
        var persisted = productStockRepository.save(product);

        // then
        var retrieved = productStockRepository.findById(persisted.getId());

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getQuantity()).isEqualTo(quantity);
        assertThat(retrieved.get().getSeller().getEmail()).isEqualTo(SELLER_EMAIL);
        assertThat(retrieved.get().getProductDetails().getDescription()).isEqualTo(DESCRIPTION);
        assertThat(retrieved.get().getProductDetails().getName()).isEqualTo(NAME);

    }
}