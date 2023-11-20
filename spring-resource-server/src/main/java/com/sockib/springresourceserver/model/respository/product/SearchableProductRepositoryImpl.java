package com.sockib.springresourceserver.model.respository.product;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.service.product.SearchFilterToProductSpecificationConverterImpl;
import com.sockib.springresourceserver.util.search.filter.Specification;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.page.SimplePageImpl;
import com.sockib.springresourceserver.util.search.sort.Sorter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
public class SearchableProductRepositoryImpl implements SearchableProductRepository {

    private final EntityManager entityManager;

    @Override
    public SimplePage<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter, String entityGraph) {
        var idsAndProductScores = getProductsIdsMatchingSpecification(specification, pageable, sorter);
        var products = getOrderedProductListFromIdList(idsAndProductScores, sorter, entityGraph);
        var productsCount = getNumberOfProductsMatchingSpecification(specification);

        var isFirstPage = pageable.getPage() == 0;
        var isLastPage = productsCount <= pageable.getOffset() + pageable.getLimit();

        return new SimplePageImpl<>(products, isFirstPage, isLastPage);
    }

    @Override
    public SimplePage<Product> findProducts(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter) {
        return findProducts(specification, pageable, sorter, "product[all]");
    }

    private List<Pair<Long, ProductScore>> getProductsIdsMatchingSpecification(Specification<Product> specification, Pageable pageable, Sorter<Product> sorter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        var order = sorter.toOrder(root, criteriaQuery, criteriaBuilder);

        Path<ProductReview> reviewJoin = root.join(Product_.PRODUCT_REVIEWS, JoinType.LEFT);

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

        var results = list.stream()
                .map(p -> Pair.of((Long) p.get(0), new ProductScore((Long) p.get(2), (Double) p.get(1))))
                .toList();

        return results;
    }

    private List<Product> getOrderedProductListFromIdList(List<Pair<Long, ProductScore>> pairs, Sorter<Product> sorter, String entityGraph) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        var ids = pairs.stream()
                .map(Pair::getSt)
                .toList();

        var order = sorter.toOrder(root, criteriaQuery, criteriaBuilder);
        var query = criteriaQuery.where(root.get(Product_.ID).in(ids))
                .orderBy(order);

        var graph = entityManager.getEntityGraph(entityGraph);
        var products = entityManager.createQuery(query)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();

        IntStream.range(0, pairs.size())
                .mapToObj(i -> Pair.of(products.get(i), pairs.get(i).getNd()))
                .forEach(pair -> pair.getSt().setProductScore(pair.getNd()));

        return products;

//        var productMap = products.stream()
//                .collect(Collectors.toMap(WithCreationAndUpdateTimestamp::getId, p -> p));
//
//        return pairs.stream()
//                .map(pair -> {
//                    var p = productMap.get(pair.getSt());
//                    p.setProductScore(pair.getNd());
//                    return p;
//                })
//                .toList();
    }

    private Long getNumberOfProductsMatchingSpecification(Specification<Product> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        var predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);

        var query = criteriaQuery
                .select(criteriaBuilder.countDistinct(root.get(Product_.ID)))
                .where(predicate);

        return entityManager.createQuery(query)
                .getSingleResult();
    }

    private record Pair<T, U>(T t, U u) {
        public T getSt() {
                       return t;
                                }
        public U getNd() {
                       return u;
                                }
        public static <T, U> Pair<T, U> of(T t, U u) {
                return new Pair<T, U>(t, u);
            }
    }
}
