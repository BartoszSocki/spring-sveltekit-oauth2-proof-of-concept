package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.service.product.SearchFilterToProductSpecificationConverter;
import com.sockib.springresourceserver.service.product.SearchFilterToProductSpecificationConverterImpl;
import com.sockib.springresourceserver.util.search.SearchFilter;
import com.sockib.springresourceserver.util.search.SearchOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    SearchFilterToProductSpecificationConverter searchFilterToProductSpecificationConverter;

    @BeforeEach
    void init() {
        searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
    }

    @Test
    @Sql("/repository/name_test_1.sql")
    void givenSearchFiltersAndPage_whenSearch_thenSuccess() {
        // given
        var filters = List.of(
                SearchFilter.builder()
                        .fieldName("name")
                        .searchOperation(SearchOperation.LIKE)
                        .fieldValue("a")
                        .build()
        );

        var specification = searchFilterToProductSpecificationConverter.convert(filters);
        var page = Pageable.ofSize(10);

        // when
        var products = productRepository.findProducts(specification, page, "product[category]");

        // then
        assertThat(products).isNotEmpty();
    }

    @Test
    @Sql("/repository/category_test_1.sql")
    void givenCategoryFilter_whenSearch_thenSuccess() {
        // given
        final String category = "Chairs";

        var filter = SearchFilter.builder()
                .fieldName("category")
                .searchOperation(SearchOperation.EQ)
                .fieldValue(category)
                .build();

        var specification = searchFilterToProductSpecificationConverter.convert(filter);
        var page = Pageable.ofSize(10);

        // when
        var products = productRepository.findProducts(specification, page, "product[category]");

        // then
        var productCategories = products.stream().map(p -> p.getCategory().getName()).toList();

        assertThat(productCategories).isNotEmpty();
        assertThat(productCategories).hasSize(2);
        assertThat(productCategories).containsOnly(category);
    }

}