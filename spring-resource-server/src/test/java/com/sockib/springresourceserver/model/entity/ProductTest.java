package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.repository.ProductRepository;
import com.sockib.springresourceserver.model.valueobject.ProductStatus;
import com.sockib.springresourceserver.model.valueobject.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    ProductDetails productDetails;
    final static Price PRICE = new Price(120D, "PLN");
    final static String NAME = "name";
    final static String DESCRIPTION = "desc";

    User seller;
    final static String SELLER_EMAIL = "email.com";

    ProductStock productStock;
    static final Integer PRODUCT_QUANTITY = 100;


    @BeforeEach
    void init() {
        productDetails = new ProductDetails();
        productDetails.setDescription(DESCRIPTION);
        productDetails.setPrice(PRICE);
        productDetails.setName(NAME);

        seller = new User();
        seller.setEmail(SELLER_EMAIL);

        productStock = new ProductStock();
        productStock.setQuantity(PRODUCT_QUANTITY);
        productStock.setSeller(seller);
        productStock.setProductDetails(productDetails);
    }

    @Test
    void givenProduct_whenPersist_thenSuccess() {
        // given
        var product = new Product();
        product.setProductDetails(productDetails);
        product.setProductStatus(ProductStatus.DEFAULT);

        // when
        var persisted = productRepository.save(product);

        // then
        var retrieved = productRepository.findById(persisted.getId());

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getProductStatus()).isEqualTo(ProductStatus.DEFAULT);
        assertThat(retrieved.get().getProductDetails().getName()).isEqualTo(NAME);
        assertThat(retrieved.get().getProductDetails().getDescription()).isEqualTo(DESCRIPTION);
        assertThat(retrieved.get().getProductDetails().getPrice()).isEqualTo(PRICE);
    }

}
