package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.value.Money;
import com.sockib.springresourceserver.model.value.Address;
import com.sockib.springresourceserver.model.entity.LineItem;
import com.sockib.springresourceserver.model.entity.Order;
import com.sockib.springresourceserver.model.respository.CategoryRepository;
import com.sockib.springresourceserver.model.respository.OrderRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Sql("/repository/transaction_test_1.sql")
    void givenBoughtProductsUserAndAddress_whenPersist_thenSuccess() {
        var transaction = new Order();

        transaction.setOrderStatus("OK");

        var user = userRepository.findUserByEmail("email.com").orElseThrow(RuntimeException::new);
        transaction.setBuyer(user);

        var address = new Address();
        address.setCountry("poland");
        address.setCity("warsaw");
        address.setPostalCode("00-000");
        address.setAddressLine("street 1");
        transaction.setAddress(address);

        var boughtProduct1 = new LineItem();
        boughtProduct1.setName("AAA");

        var price = new Money();
        price.setAmount(100.0);
        price.setCurrency("USD");

        boughtProduct1.setPrice(price);

        var category = categoryRepository.findCategoryByName("AAA").orElseThrow(RuntimeException::new);
        boughtProduct1.setCategory(category);

        transaction.setLineItems(List.of(boughtProduct1));

        var persistedTransaction = orderRepository.save(transaction);

        assertThat(persistedTransaction).isNotNull();
        assertThat(persistedTransaction.getLineItems()).isNotNull();
        assertThat(persistedTransaction.getLineItems()).isNotEmpty();
    }

}
