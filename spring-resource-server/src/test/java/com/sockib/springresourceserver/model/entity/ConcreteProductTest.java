package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.repository.ConcreteProductRepository;
import com.sockib.springresourceserver.model.valueobject.ConcreteProductStatus;
import com.sockib.springresourceserver.model.valueobject.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConcreteProductTest {

    @Autowired
    ConcreteProductRepository concreteProductRepository;

    ProductDetails productDetails;
    final static Price PRICE = new Price(120D, "PLN");
    final static String DESCRIPTION = "desc";

    User seller;
    final static String SELLER_EMAIL = "email.com";

    Product product;
    final static String PRODUCT_NAME = "name";
    static final Integer PRODUCT_QUANTITY = 100;


    @BeforeEach
    void init() {
        productDetails = new ProductDetails();
        productDetails.setDescription(DESCRIPTION);
        productDetails.setPrice(PRICE);

        seller = new User();
        seller.setEmail(SELLER_EMAIL);

        product = new Product();
        product.setName(PRODUCT_NAME);
        product.setQuantity(PRODUCT_QUANTITY);
        product.setSeller(seller);
        product.setProductDetails(productDetails);
    }

    @Test
    void givenConcreteProduct_whenPersist_thenSuccess() {
        // given
        var concreteProduct = new ConcreteProduct();
        concreteProduct.setProduct(product);
        concreteProduct.setProductDetails(productDetails);
        concreteProduct.setConcreteProductStatus(ConcreteProductStatus.DEFAULT);

        // when
        var persisted = concreteProductRepository.save(concreteProduct);

        // then
        var retrieved = concreteProductRepository.findById(persisted.getId());

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getConcreteProductStatus()).isEqualTo(ConcreteProductStatus.DEFAULT);
        assertThat(retrieved.get().getProduct().getName()).isEqualTo(PRODUCT_NAME);
        assertThat(retrieved.get().getProduct().getQuantity()).isEqualTo(PRODUCT_QUANTITY);
        assertThat(retrieved.get().getProductDetails().getDescription()).isEqualTo(DESCRIPTION);
        assertThat(retrieved.get().getProductDetails().getPrice()).isEqualTo(PRICE);
    }

}
