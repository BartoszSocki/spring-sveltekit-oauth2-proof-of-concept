package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.model.value.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
public class SearchableProductRepository2Impl implements SearchableProductRepository2 {

    private final EntityManager entityManager;

    @Override
    public List<Product> findProducts(Specification<Product> whereSpecification, Specification<Product> havingSpecification, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var wherePredicate = whereSpecification.toPredicate(root, criteriaQuery, criteriaBuilder);
        var havingPredicate = havingSpecification.toPredicate(root, criteriaQuery, criteriaBuilder);

        // TODO: find nice way to implement predefined orders
        Order order = criteriaBuilder.asc(criteriaBuilder.avg(root.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)));

        var query = criteriaQuery
                .multiselect(
                        root,
                        criteriaBuilder.avg(
                                root.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)
                        ),
                        criteriaBuilder.count(
                                root.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)
                        )
                )
                .where(wherePredicate)
                .having(havingPredicate)
                .orderBy(order)
                .groupBy(
                        root.get(Product_.ID)
                );

        var list = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        var products = list.stream()
                .map(tuple -> {
                    Product product = (Product) tuple.get(0);
                    product.setProductScore(new ProductScore((Long) tuple.get(2), (Double) tuple.get(1)));

                    return product;
                })
                .toList();

        return products;
    }

}
