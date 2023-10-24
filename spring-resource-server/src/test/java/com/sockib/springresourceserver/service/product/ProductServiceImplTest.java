package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.model.respository.SearchableProductRepository;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.SearchOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductServiceImplTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Sql("/service/product/data1.sql")
    void givenSearchFiltersAndPage_whenSearch_thenSuccess() {
        // given
        var filters = List.of(
                SearchFilter.builder()
                        .fieldName("name")
                        .searchOperation(SearchOperation.LIKE)
                        .fieldValue("a")
                        .build()
        );

        var specification = ProductSearch.resolve(filters);
        var page = Pageable.ofSize(2);

        // when
        var products = productRepository.findProducts(specification, page);

        // then
//        products.forEach(System.out::println);
    }

}