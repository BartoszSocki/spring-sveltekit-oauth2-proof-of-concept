package com.sockib.springresourceserver.model.entity;
import com.sockib.springresourceserver.model.repository.ProductRepository;
import com.sockib.springresourceserver.model.repository.ReviewRepository;
import com.sockib.springresourceserver.model.valueobject.ProductStatus;
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
public class ProductReviewTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;

    ProductDetails productDetails;
    final static Price PRICE = new Price(120D, "PLN");
    final static String NAME = "name";
    final static String DESCRIPTION = "desc";

    User seller;
    final static String SELLER_EMAIL = "email.com";

    ProductStock productStock;
    final static Integer PRODUCT_QUANTITY = 100;

    Product product;
    final static ProductStatus STATUS = ProductStatus.DEFAULT;

    Product persistedProduct;

    final static FiveStarScore FIVE_STAR_SCORE = new FiveStarScore(3);

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

        product = new Product();
        product.setProductDetails(productDetails);
        product.setProductStatus(STATUS);

        persistedProduct = productRepository.save(product);
    }

    @Test
    void givenProductReview_whenPersist_thenSuccess() {
        // given
        var productReview = new ProductReview();
        productReview.setFiveStarScore(FIVE_STAR_SCORE);
        productReview.setProduct(product);

        // when
        var persisted = reviewRepository.save(productReview);

        // then
        var retrieved = reviewRepository.findById(persisted.getId());

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getFiveStarScore()).isEqualTo(FIVE_STAR_SCORE);
        assertThat(((ProductReview) retrieved.get()).getProduct().getProductStatus()).isEqualTo(STATUS);
    }
}
