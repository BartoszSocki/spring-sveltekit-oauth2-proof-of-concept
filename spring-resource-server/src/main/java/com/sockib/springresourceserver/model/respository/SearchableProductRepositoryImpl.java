package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.util.search.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SearchableProductRepositoryImpl implements SearchableProductRepository {

    private final EntityManager entityManager;

    @Override
    public Page<Product> findProducts(Specification<Product> specification, Pageable pageable, Sort sort, String entityGraphName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        Path<ProductReview> reviewJoin = root.join(Product_.PRODUCT_REVIEWS, JoinType.LEFT);

        var order = getOrder(criteriaBuilder, root, sort);

        var query = criteriaQuery
                .multiselect(
                        root.get(Product_.ID),
                        criteriaBuilder.avg(
                                reviewJoin.get(ProductReview_.FIVE_STAR_SCORE)
                        ),
                        criteriaBuilder.count(
                                reviewJoin.get(ProductReview_.FIVE_STAR_SCORE)
                        )
                )
                .where(predicate)
                .orderBy(order)
                .groupBy(
                        root.get(Product_.ID)
                );

        var list = entityManager.createQuery(query)
                .setFirstResult(pageable.getOffset().intValue())
                .setMaxResults(pageable.getLimit().intValue())
                .getResultList();

        List<Long> sortedProductsIds = list.stream().map(t -> (Long) t.get(0)).toList();

        CriteriaBuilder productCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = productCriteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        var productQuery = productCriteriaQuery.where(productRoot.get(Product_.ID).in(sortedProductsIds));

        var graph = entityManager.getEntityGraph(entityGraphName);
        var products = entityManager.createQuery(productQuery)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();

        Map<Long, ProductScore> productScores = list.stream().collect(Collectors.toMap(t -> (Long) t.get(0), t -> new ProductScore((Long) t.get(2), (Double) t.get(1))));
        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));

        var productsContent = sortedProductsIds.stream().map(i -> {
            Product p = productMap.get(i);
            p.setProductScore(productScores.get(i));
            return p;
        }).toList();

        // get how many products there are
        CriteriaBuilder countCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteriaQuery = countCriteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countCriteriaQuery.from(Product.class);

        var countPredicate = specification.toPredicate(countRoot, countCriteriaQuery, countCriteriaBuilder);
        countCriteriaQuery.where(countPredicate).select(criteriaBuilder.count(countRoot.get(Product_.ID)));
        Long count = entityManager.createQuery(countCriteriaQuery).getSingleResult();

        return new PageImpl<>(productsContent, (long) pageable.getPage(), pageable.getLimit(),count);
    }

    @Override
    public Page<Product> findProducts(Specification<Product> specification, Pageable pageable, Sort sort) {
        return findProducts(specification, pageable, sort, "product[all]");
    }

    private Order getOrder(CriteriaBuilder criteriaBuilder, Path<Product> productPath, Sort sort) {
        var field = switch (sort.getFieldName()) {
            case "price" -> productPath.get(Product_.PRICE).get("amount");
            case "name" -> criteriaBuilder.lower(productPath.get(Product_.NAME));
            case "score" -> criteriaBuilder.literal(2);
            default -> throw new RuntimeException("TODO: add custom exception");
        };

        return sort.getSortDirection() == SortDirection.DSC ? criteriaBuilder.desc(field) : criteriaBuilder.asc(field);
    }

}
