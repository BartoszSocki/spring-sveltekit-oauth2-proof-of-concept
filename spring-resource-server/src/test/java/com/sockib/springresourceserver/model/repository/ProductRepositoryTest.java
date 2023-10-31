package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.Category;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductInventory;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.service.product.SearchFilterToProductSpecificationConverter;
import com.sockib.springresourceserver.service.product.SearchFilterToProductSpecificationConverterImpl;
import com.sockib.springresourceserver.util.search.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

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
        var page = Pageable.of(0L, 10L);

        // when
        var products = productRepository.findProducts(specification, page, "product[category]");

        // then
        assertThat(products.getContent()).isNotEmpty();
    }

    @Test
    @Sql("/repository/category_test_1.sql")
    void givenCategoryFilter_whenSearch_thenSuccess() {
        // given
        final String category = "NotChairs";

        var filter = SearchFilter.builder()
                .fieldName("category")
                .searchOperation(SearchOperation.EQ)
                .fieldValue(category)
                .build();

        var specification = searchFilterToProductSpecificationConverter.convert(filter);
        var page = Pageable.of(0L, 10L);

        // when
        var products = productRepository.findProducts(specification, page, "product[category]").getContent();

        // then
        var productCategories = products.stream().map(p -> p.getCategory().getName()).toList();

        assertThat(productCategories).isNotEmpty();
        assertThat(productCategories).hasSize(2);
        assertThat(productCategories).containsOnly(category);
    }

    @Test
    @Sql("/repository/tags_test_1.sql")
    void givenTagsFilter_whenSearch_thenSuccess() {
        // given
        final String tags = "Poland";

        var filter = SearchFilter.builder()
                .fieldName("tag")
                .searchOperation(SearchOperation.IN)
                .fieldValue(tags)
                .build();

        var specification = searchFilterToProductSpecificationConverter.convert(filter);
        var page = Pageable.of(0L, 10L);

        // when
        var products = productRepository.findProducts(specification, page, "product[all]").getContent();

        // then
        assertThat(products).isNotEmpty();
    }

    @Test
    @Sql("/repository/add_new_product_test_1.sql")
    void givenProduct_whenPersist_thenSuccess() {
        // given
        var owner = userRepository.findById(100L).orElseThrow(RuntimeException::new);

        var product = Product.builder()
                .name("beer")
                .description("students drink")
                .inventory(new ProductInventory(1))
                .category(new Category("NotBeverage"))
                .owner(owner)
                .price(new Money(1.0, "USD"))
                .build();

        // when
        productRepository.save(product);

        // then
        assert true;
    }

    @Test
    @Sql("/repository/name_test_1.sql")
    void givenAscSortByName_whenSearch_thenSuccess() {
        // given
        Sort sort = Sort.of("name", SortDirection.ASC);
        Pageable pageable = Pageable.of(0L, 10L);

        // when
        var products = productRepository.findProducts(Specification.empty(), pageable, sort).getContent();

        // then
        assertThat(products).isNotEmpty();

        var sortedNames = products.stream().map(Product::getName).sorted().toList();
        var fetchedNames = products.stream().map(Product::getName).toList();

        assertThat(sortedNames).isEqualTo(fetchedNames);
    }

    @Test
    @Sql("/repository/name_test_1.sql")
    void givenAscSortByPrice_whenSearch_thenSuccess() {
        // given
        Sort sort = Sort.of("price", SortDirection.ASC);
        Pageable pageable = Pageable.of(0L, 10L);

        // when
        var products = productRepository.findProducts(Specification.empty(), pageable, sort).getContent();

        // then
        assertThat(products).isNotEmpty();

        var sortedPrices = products.stream().map(p -> p.getPrice().getAmount()).sorted().toList();
        var fetchedPrices = products.stream().map(p -> p.getPrice().getAmount()).toList();

        sortedPrices.forEach(System.out::println);
        fetchedPrices.forEach(System.out::println);

        assertThat(sortedPrices).isEqualTo(fetchedPrices);
    }

    @Test
    @Sql("/repository/name_test_1.sql")
    void givenAscSortByScore_whenSearch_thenSuccess() {
        // given
        Sort sort = Sort.of("score", SortDirection.ASC);
        Pageable pageable = Pageable.of(0L, 10L);

        // when
        var products = productRepository.findProducts(Specification.empty(), pageable, sort).getContent();

        // then
        assertThat(products).isNotEmpty();

        var sortedScores = products.stream().map(p -> p.getProductScore().getAverageScore()).sorted().toList();
        var fetchedScores = products.stream().map(p -> p.getProductScore().getAverageScore()).toList();

        assertThat(sortedScores).isEqualTo(fetchedScores);
    }
}