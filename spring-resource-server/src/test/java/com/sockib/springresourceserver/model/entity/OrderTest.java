package com.sockib.springresourceserver.model.entity;
import com.sockib.springresourceserver.model.repository.OrderRepository;
import com.sockib.springresourceserver.model.valueobject.Address;
import com.sockib.springresourceserver.model.valueobject.OrderStatus;
import com.sockib.springresourceserver.model.valueobject.Price;
import com.sockib.springresourceserver.model.valueobject.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    ProductDetails productDetails;
    final static Price PRICE = new Price(120D, "PLN");
    final static String NAME = "name";
    final static String DESCRIPTION = "desc";

    User seller;
    final static String SELLER_EMAIL = "email1.com";

    User buyer;
    final static String BUYER_EMAIL = "email2.com";

    ProductStock productStock;
    final static Integer PRODUCT_QUANTITY = 100;

    Product product;
    final static ProductStatus PRODUCT_STATUS = ProductStatus.DEFAULT;

    @BeforeEach
    void init() {
        productDetails = new ProductDetails();
        productDetails.setDescription(DESCRIPTION);
        productDetails.setPrice(PRICE);
        productDetails.setName(NAME);

        seller = new User();
        seller.setEmail(SELLER_EMAIL);

        buyer = new User();
        buyer.setEmail(BUYER_EMAIL);

        productStock = new ProductStock();
        productStock.setQuantity(PRODUCT_QUANTITY);
        productStock.setSeller(seller);
        productStock.setProductDetails(productDetails);

        product = new Product();
        product.setProductDetails(productDetails);
        product.setProductStatus(PRODUCT_STATUS);
    }

    @Test
    void givenOrder_whenPersist_thenSuccess() {
        // given
        final OrderStatus status = OrderStatus.DEFAULT;
        final Address address = new Address("address");
        var order = Order.builder()
                .price(PRICE)
                .buyer(buyer)
                .seller(seller)
                .product(product)
                .address(address)
                .orderStatus(status)
                .build();

        // when
        var persisted = orderRepository.save(order);

        // then
        var retrieved = orderRepository.findById(persisted.getId());

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getOrderStatus()).isEqualTo(status);
        assertThat(retrieved.get().getPrice()).isEqualTo(PRICE);
        assertThat(retrieved.get().getAddress()).isEqualTo(address);
    }

}
