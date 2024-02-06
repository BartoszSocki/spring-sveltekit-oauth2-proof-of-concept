package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.domain.product.ProductSpecificationFactory;
import com.sockib.springresourceserver.domain.product.data.ProductQueryCriteria;
import com.sockib.springresourceserver.domain.product.data.ProductSpecification;
import com.sockib.springresourceserver.model.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SearchableProductRepositoryImplTest {

    @Autowired
    @Qualifier("searchableProductRepository2Impl")
    private SearchableProductRepository2 productRepository;

    @Test
    void test1() {
        ProductQueryCriteria criteria = ProductQueryCriteria.builder()
                .minPrice(3.0)
                .minScore(3)
                .build();

        Specification<Product> whereSpecification = ProductSpecificationFactory.where(criteria);
        Specification<Product> havingSpecification = ProductSpecificationFactory.having(criteria);

        List<Product> products = productRepository.findProducts(whereSpecification, havingSpecification, PageRequest.of(0, 10));
        Assertions.assertNotNull(products);

        System.out.println(products);
    }

    @Test
    void test2() {
        Pageable pageable = PageRequest.of(0, 10);
        pageable = pageable.next();
        pageable = pageable.next();
        System.out.println(pageable.getOffset());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
    }

}