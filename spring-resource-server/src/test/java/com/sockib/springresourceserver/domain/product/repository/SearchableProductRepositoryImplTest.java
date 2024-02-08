package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.domain.product.factory.ProductSorterFactory;
import com.sockib.springresourceserver.domain.product.factory.ProductSpecificationFactory;
import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSortCriteria;
import com.sockib.springresourceserver.domain.product.query.ProductSpecification;
import com.sockib.springresourceserver.domain.product.query.ProductSpecifications;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.respository.product.SearchableProductRepository;
import com.sockib.springresourceserver.util.search.sort.Sorter;
import graphql.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        ProductSpecification specification = ProductSpecificationFactory.where(criteria);
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
    void givenName_whenQuery_thenReturnSpecifiedProducts() {
        // given
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .name("1KG")
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.where(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        System.out.println(products);
    }

    @Test
    void givenCategory_whenQuery_thenReturnSpecifiedProducts() {
        // given
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .category("Games")
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.where(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        System.out.println(products.size());
        products.forEach(p -> System.out.println(p.getName() + " " + p.getDescription()));

        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream().allMatch(p -> "Games".equals(p.getCategory().getName())));
    }

    @Test
    void givenTags_whenQuery_thenReturnSpecifiedProducts() {
        // given
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .tags(Set.of("Fruits", "Bio"))
                .build();

        // when
        ProductSpecification specification = ProductSpecificationFactory.where(criteria);
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(specification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(1, products.size());
    }

}