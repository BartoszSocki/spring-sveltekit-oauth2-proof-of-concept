package com.sockib.springresourceserver.model.entity;
import com.sockib.springresourceserver.model.repository.ConcreteProductRepository;
import com.sockib.springresourceserver.model.repository.ReviewRepository;
import com.sockib.springresourceserver.model.valueobject.ConcreteProductStatus;
import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import com.sockib.springresourceserver.model.valueobject.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConcreteProductReviewTest {

    @Autowired
    ConcreteProductRepository concreteProductRepository;
    @Autowired
    ReviewRepository reviewRepository;

    ProductDetails productDetails;
    final static Price PRICE = new Price(120D, "PLN");
    final static String DESCRIPTION = "desc";

    User seller;
    final static String SELLER_EMAIL = "email.com";

    Product product;
    final static String PRODUCT_NAME = "name";
    final static Integer PRODUCT_QUANTITY = 100;

    ConcreteProduct concreteProduct;
    final static ConcreteProductStatus STATUS = ConcreteProductStatus.DEFAULT;

    ConcreteProduct persistedConcreteProduct;

    final static FiveStarScore FIVE_STAR_SCORE = new FiveStarScore(3);

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

        concreteProduct = new ConcreteProduct();
        concreteProduct.setProduct(product);
        concreteProduct.setProductDetails(productDetails);
        concreteProduct.setConcreteProductStatus(STATUS);

        persistedConcreteProduct = concreteProductRepository.save(concreteProduct);
    }

    @Test
    void givenConcreteProductReview_whenPersist_thenSuccess() {
        // given
        var productReview = new ProductReview();
        productReview.setFiveStarScore(FIVE_STAR_SCORE);
        productReview.setConcreteProduct(concreteProduct);

        // when
        var persisted = reviewRepository.save(productReview);

        // then
        var retrieved = reviewRepository.findById(persisted.getId());

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getFiveStarScore()).isEqualTo(FIVE_STAR_SCORE);
        assertThat(((ProductReview) retrieved.get()).getConcreteProduct().getConcreteProductStatus()).isEqualTo(STATUS);
    }
}
