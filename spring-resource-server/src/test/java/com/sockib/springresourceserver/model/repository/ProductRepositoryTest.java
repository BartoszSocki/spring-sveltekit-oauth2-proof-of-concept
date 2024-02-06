package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.respository.product.ProductRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.service.product.SearchFilterToProductSpecificationConverter;
import com.sockib.springresourceserver.service.product.impl.SearchFilterToProductSpecificationConverterImpl;
import com.sockib.springresourceserver.service.product.SortToProductSorterConverter;
import com.sockib.springresourceserver.service.product.impl.SortToProductSorterConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

//    SearchFilterToProductSpecificationConverter searchFilterToProductSpecificationConverter;
//    SortToProductSorterConverter sortToProductSorterConverter;
//
//    @BeforeEach
//    void init() {
//        searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
//        sortToProductSorterConverter = new SortToProductSorterConverterImpl();
//    }
//
//    @Test
//    @Sql("/repository/name_test_1.sql")
//    void givenSearchFiltersAndPage_whenSearch_thenSuccess() {
//        // given
//        var filters = List.of(
//                SearchFilter.builder()
//                        .fieldName("name")
//                        .searchOperation(SearchOperation.LIKE)
//                        .fieldValue("a")
//                        .build()
//        );
//
//        var specification = searchFilterToProductSpecificationConverter.convert(filters);
//        var sorter = sortToProductSorterConverter.convert(Sort.of("name", SortDirection.ASC));
//        var page = Pageable.of(0L, 10L);
//
//        // when
//        var products = productRepository.findProducts(specification, page, sorter);
//
//        // then
//        assertThat(products.getContent()).isNotEmpty();
//    }
//
//    @Test
//    @Sql("/repository/category_test_1.sql")
//    void givenCategoryFilter_whenSearch_thenSuccess() {
//        // given
//        final String category = "NotChairs";
//
//        var filter = SearchFilter.builder()
//                .fieldName("category")
//                .searchOperation(SearchOperation.EQ)
//                .fieldValue(category)
//                .build();
//
//        var specification = searchFilterToProductSpecificationConverter.convert(filter);
//        var sorter = sortToProductSorterConverter.convert(Sort.of("name", SortDirection.ASC));
//        var page = Pageable.of(0L, 10L);
//
//        // when
//        var products = productRepository.findProducts(specification, page, sorter).getContent();
//
//        // then
//        var productCategories = products.stream().map(p -> p.getCategory().getName()).toList();
//
//        assertThat(productCategories).isNotEmpty();
//        assertThat(productCategories).hasSize(2);
//        assertThat(productCategories).containsOnly(category);
//    }
//
//    @Test
//    @Sql("/repository/tags_test_1.sql")
//    void givenTagsFilter_whenSearch_thenSuccess() {
//        // given
//        final String tags = "Poland";
//
//        var filter = SearchFilter.builder()
//                .fieldName("tag")
//                .searchOperation(SearchOperation.IN)
//                .fieldValue(tags)
//                .build();
//
//        var specification = searchFilterToProductSpecificationConverter.convert(filter);
//        var sorter = sortToProductSorterConverter.convert(Sort.of("name", SortDirection.ASC));
//        var page = Pageable.of(0L, 10L);
//
//        // when
//        var products = productRepository.findProducts(specification, page, sorter).getContent();
//
//        // then
//        assertThat(products).isNotEmpty();
//    }
//
//    @Test
//    @Sql("/repository/add_new_product_test_1.sql")
//    void givenProduct_whenPersist_thenSuccess() {
//        // given
//        var owner = userRepository.findById(100L).orElseThrow(RuntimeException::new);
//
//        var product = Product.builder()
//                .name("beer")
//                .description("students drink")
//                .inventory(new ProductInventory(1))
//                .category(new Category("NotBeverage"))
//                .owner(owner)
//                .price(new Money(1.0, "USD"))
//                .build();
//
//        // when
//        productRepository.save(product);
//
//        // then
//        assert true;
//    }
//
//    @Test
//    @Sql("/repository/name_test_1.sql")
//    void givenAscSortByName_whenSearch_thenSuccess() {
//        // given
//        var sorter = sortToProductSorterConverter.convert(Sort.of("name", SortDirection.ASC));
//        var pageable = Pageable.of(0L, 10L);
//
//        // when
//        var products = productRepository.findProducts(Specification.empty(), pageable, sorter).getContent();
//
//        // then
//        assertThat(products).isNotEmpty();
//
//        var sortedNames = products.stream().map(Product::getName).sorted(String::compareToIgnoreCase).toList();
//        var fetchedNames = products.stream().map(Product::getName).toList();
//
//        assertThat(sortedNames).isEqualTo(fetchedNames);
//    }
//
//    @Test
//    @Sql("/repository/name_test_1.sql")
//    void givenAscSortByPrice_whenSearch_thenSuccess() {
//        // given
//        var sorter = sortToProductSorterConverter.convert(Sort.of("price", SortDirection.ASC));
//        var pageable = Pageable.of(0L, 10L);
//
//        // when
//        var products = productRepository.findProducts(Specification.empty(), pageable, sorter).getContent();
//
//        // then
//        assertThat(products).isNotEmpty();
//
//        var sortedPrices = products.stream().map(p -> p.getPrice().getAmount()).sorted().toList();
//        var fetchedPrices = products.stream().map(p -> p.getPrice().getAmount()).toList();
//
//        assertThat(sortedPrices).isEqualTo(fetchedPrices);
//    }
//
//    @Test
//    @Sql("/repository/name_test_1.sql")
//    void givenAscSortByScore_whenSearch_thenSuccess() {
//        // given
//        var sorter = sortToProductSorterConverter.convert(Sort.of("score", SortDirection.ASC));
//        var pageable = Pageable.of(0L, 10L);
//
//        // when
//        var products = productRepository.findProducts(Specification.empty(), pageable, sorter).getContent();
//
//        // then
//        assertThat(products).isNotEmpty();
//
//        var sortedScores = products.stream().map(p -> p.getProductScore().getAverageScore()).sorted().toList();
//        var fetchedScores = products.stream().map(p -> p.getProductScore().getAverageScore()).toList();
//
//        assertThat(sortedScores).isEqualTo(fetchedScores);
//    }
//
//    @Test
//    @Sql("/repository/product_ids_test_1.sql")
//    void givenProductsIds_whenFind_thenSuccess() {
//        // given
//        final var ids = List.of(100L, 200L);
//
//        // when
//        var products = productRepository.findProductsByIdIn(ids);
//
//        // then
//        assertThat(products).isNotNull();
//        assertThat(products).isNotEmpty();
//        assertThat(products).hasSize(ids.size());
//    }

}