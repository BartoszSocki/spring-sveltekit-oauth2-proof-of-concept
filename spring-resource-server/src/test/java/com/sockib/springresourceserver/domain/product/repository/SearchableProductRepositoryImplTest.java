package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.domain.product.query.ProductSortCriteria;
import com.sockib.springresourceserver.domain.product.factory.ProductSorterFactory;
import com.sockib.springresourceserver.domain.product.factory.ProductSpecificationFactory;
import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.respository.product.SearchableProductRepository;
import com.sockib.springresourceserver.util.search.sort.Sorter;
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
        Specification<Product> whereSpecification = ProductSpecificationFactory.where(criteria);
        Specification<Product> havingSpecification = ProductSpecificationFactory.having(criteria);
        Sorter<Product> sorter = ProductSorterFactory.create(criteria);

        List<Product> products = productRepository.findProducts(whereSpecification, havingSpecification, sorter, PageRequest.of(0, 10));

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
    void givenCategory_whenQuery_thenReturnSpecifiedProducts() {
        // given
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .category("Games")
                .build();

        // when
        Specification<Product> whereSpecification = ProductSpecificationFactory.where(criteria);
        Specification<Product> havingSpecification = ProductSpecificationFactory.empty();
        Sorter<Product> sorter = ProductSorterFactory.noSort();

        List<Product> products = productRepository.findProducts(whereSpecification, havingSpecification, sorter, PageRequest.of(0, 10));

        // then
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream().allMatch(p -> "Games".equals(p.getCategory().getName())));
    }

}