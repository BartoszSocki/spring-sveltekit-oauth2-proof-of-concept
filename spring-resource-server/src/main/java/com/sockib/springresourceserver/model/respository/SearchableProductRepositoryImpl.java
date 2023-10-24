package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.util.search.Specification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SearchableProductRepositoryImpl implements SearchableProductRepository {

    private final EntityManager entityManager;

    @Override
    public List<Product> findProducts(Specification<Product> specification, Pageable pageable, String entityGraphName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);

        var query = criteriaQuery
                .multiselect(
                        root.get(Product_.ID),
                        criteriaBuilder.avg(
                                root.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)
                        ),
                        criteriaBuilder.count(
                                root.get(Product_.ID)
                        )
                )
                .where(predicate)
                .groupBy(
                        root.get(Product_.ID)
                );

        if (pageable.isUnpaged()) {
            throw new RuntimeException("problem with paging");
        }

        var list = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

//        List<Pair<Long, Double>> pairs = list.stream().map(t -> Pair.of((Long) t.get(0), (Double) t.get(1))).toList();
        Map<Long, ProductScore> productScores = list.stream().collect(Collectors.toMap(t -> (Long) t.get(0), t -> new ProductScore((Long) t.get(2), (Double) t.get(1))));
        List<Long> ids = list.stream().map(t -> (Long) t.get(0)).toList();

        CriteriaBuilder productCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = productCriteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        var productQuery = productCriteriaQuery.where(productRoot.get(Product_.ID).in(ids));

        var graph = entityManager.getEntityGraph(entityGraphName);

        var products = entityManager.createQuery(productQuery)
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
        products.forEach(p -> p.setProductScore(productScores.get(p.getId())));
        return products;
    }

}