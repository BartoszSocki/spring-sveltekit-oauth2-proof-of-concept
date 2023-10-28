package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.util.search.Page;
import com.sockib.springresourceserver.util.search.Sort;
import com.sockib.springresourceserver.util.search.SortDirection;
import com.sockib.springresourceserver.util.search.Specification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SearchableProductRepositoryImpl implements SearchableProductRepository {

    private final EntityManager entityManager;

    @Override
    public List<Product> findProducts(Specification<Product> specification, Page page, Sort sort, String entityGraphName) {
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
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getLimit())
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

        return sortedProductsIds.stream().map(i -> {
            Product p = productMap.get(i);
            p.setProductScore(productScores.get(i));
            return p;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Product> findProducts(Specification<Product> specification, Page page, Sort sort) {
        return findProducts(specification, page, sort, "product[all]");
    }

    @Override
    public List<Product> findProducts(Specification<Product> specification, Page page, String entityGraphName) {
        return findProducts(specification, page, Sort.of("name", SortDirection.ASC), entityGraphName);
    }

    @Override
    public List<Product> findProducts(Specification<Product> specification, Page page) {
        return findProducts(specification, page, "product[all]");
    }

    private Order getOrder(CriteriaBuilder criteriaBuilder, Path<Product> productPath, Sort sort) {
        var field = switch (sort.getFieldName()) {
            case "price" -> productPath.get(Product_.PRICE).get("amount");
            case "name" -> productPath.get(Product_.NAME);
            case "score" -> criteriaBuilder.literal(2);
            default -> throw new RuntimeException("TODO: add custom exception");
        };

        return sort.getSortDirection() == SortDirection.DSC ? criteriaBuilder.desc(field) : criteriaBuilder.asc(field);
    }

}
