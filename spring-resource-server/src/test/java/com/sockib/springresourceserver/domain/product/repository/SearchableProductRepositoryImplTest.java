package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.domain.product.factory.ProductSorterFactory;
import com.sockib.springresourceserver.domain.product.factory.ProductSpecificationFactory;
import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSortCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSpecification;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Tag;
import com.sockib.springresourceserver.model.respository.product.SearchableProductRepository;
import com.sockib.springresourceserver.core.util.Sorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SearchableProductRepositoryImplTest {

    @Autowired
    private SearchableProductRepository productRepository;

    @Test
    void givenCriteria_whenQuery_thenReturnSpecifiedProducts() {
        // given
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .minPrice(3.0)
                .minScore(3)
                .sortCriteria(ProductSortCriteria.asc(ProductSortCriteria.Field.SCORE))
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.create(criteria);

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        var scores = products.stream().map(p -> p.getProductScore().getAverageScore()).toList();
        var sortedScores = new ArrayList<>(scores);
        Collections.sort(sortedScores);

        Assertions.assertEquals(scores, sortedScores);
        Assertions.assertTrue(products.stream().allMatch(p -> p.getProductScore().getAverageScore() >= 3.0));
        Assertions.assertTrue(products.stream().allMatch(p -> p.getPrice().getAmount() >= 3.0));
    }

    @Test
    void givenNameCriteria_whenQuery_thenReturnSpecifiedProducts() {
        // given
        final String name = "1KG";
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .name(name)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream().allMatch(p -> p.getName().contains(name)));
    }

    @Test
    void givenCategoryCriteria_whenQuery_thenReturnSpecifiedProducts() {
        // given
        final String category = "Games";
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .category(category)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream().allMatch(p -> category.equals(p.getCategory().getName())));
    }

    @Test
    void givenTagsCriteria_whenQuery_thenReturnSpecifiedProducts() {
        // given
        final Set<String> tags = Set.of("Fruits", "Bio");
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .tags(tags)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(1, products.size());
        Assertions.assertTrue(products.stream()
                .map(p -> p.getTags().stream().map(Tag::getName).toList())
                .allMatch(pt -> pt.containsAll(tags))
        );
    }

    @Test
    void givenPriceCriteria_whenQuery_thenReturnSpecifiedProducts() {
        // given
        final double minPrice = 10;
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .minPrice(minPrice)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream().allMatch(p -> p.getPrice().getAmount() >= minPrice));
    }

    @Test
    void givenScoreCriteria_whenQuery_thenReturnSpecifiedProducts() {
        // given
        final int minScore = 3;
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .minScore(minScore)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream().allMatch(p -> p.getProductScore().getAverageScore() >= minScore));

    }

    @Test
    void givenPriceSortCriteria_whenQuery_thenReturnProductsInSpecifiedOrder() {
        // given
        final ProductSortCriteria sortCriteria = ProductSortCriteria.asc(ProductSortCriteria.Field.PRICE);
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .sortCriteria(sortCriteria)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.create(criteria);

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());

        var prices = products.stream().map(p -> p.getPrice().getAmount()).toList();
        var sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);

        Assertions.assertEquals(prices, sortedPrices);
    }

    @Test
    void givenScoreSortCriteria_whenQuery_thenReturnProductsInSpecifiedOrder() {
        // given
        final ProductSortCriteria sortCriteria = ProductSortCriteria.asc(ProductSortCriteria.Field.SCORE);
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .sortCriteria(sortCriteria)
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.create(criteria);
        Sorter<Product> sorter = ProductSorterFactory.create(criteria);

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());

        var scores = products.stream().map(p -> p.getProductScore().getAverageScore()).toList();
        var sortedScores = new ArrayList<>(scores);
        Collections.sort(sortedScores);

        Assertions.assertEquals(scores, sortedScores);
    }

}