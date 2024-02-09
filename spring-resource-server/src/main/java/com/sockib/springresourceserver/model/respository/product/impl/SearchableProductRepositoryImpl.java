package com.sockib.springresourceserver.model.respository.product.impl;

import com.sockib.springresourceserver.domain.product.query.ProductSpecification;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.model.respository.product.SearchableProductRepository;
import com.sockib.springresourceserver.model.value.ProductScore;
import com.sockib.springresourceserver.core.util.Sorter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
public class SearchableProductRepositoryImpl implements SearchableProductRepository {

    private final EntityManager entityManager;

    @Override
    public List<Product> findProducts(ProductSpecification specification, Sorter<Product> sorter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var wherePredicate = specification.getWhereSpecification().toPredicate(root, criteriaQuery, criteriaBuilder);
        var havingPredicate = specification.getHavingSpecification().toPredicate(root, criteriaQuery, criteriaBuilder);

        var order = sorter.toOrder(root, criteriaBuilder);

        var query = criteriaQuery
                .multiselect(
                        root,
                        criteriaBuilder.avg(
                                root.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)
                        ),
                        criteriaBuilder.countDistinct(
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

        return list.stream()
                .map(tuple -> {
                    Product product = (Product) tuple.get(0);
                    product.setProductScore(new ProductScore((Long) tuple.get(2), (Double) tuple.get(1)));

                    return product;
                })
                .toList();
    }

}
