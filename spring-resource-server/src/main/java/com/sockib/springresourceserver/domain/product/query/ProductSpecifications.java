package com.sockib.springresourceserver.domain.product.query;

import com.sockib.springresourceserver.model.entity.*;
import com.sockib.springresourceserver.model.value.ProductScore;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class ProductSpecifications {

    public static ProductSpecification nameLike(String name) {
        return name == null ? ProductSpecification.empty() : ProductSpecification.where(
                (path, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(path.get(Product_.NAME)), "%" + name.toLowerCase() + "%"));
    }

    public static ProductSpecification priceBetween(Double minPrice, Double maxPrice) {
        Double lower = minPrice == null ? 0 : minPrice;
        Double upper = maxPrice == null ? Double.MAX_VALUE : maxPrice;
        boolean areBothNull = minPrice == null && maxPrice == null;

        return areBothNull ? ProductSpecification.empty() : ProductSpecification.where(
                (path, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.between(path.get(Product_.PRICE).get("amount"), lower, upper));
    }

    public static ProductSpecification scoreBetween(Integer minScore, Integer maxScore) {
        Double lower = minScore == null ? ProductScore.MIN_SCORE : minScore;
        Double upper = maxScore == null ? ProductScore.MAX_SCORE : maxScore;
        boolean areBothNull = minScore == null && maxScore == null;

        return areBothNull ? ProductSpecification.empty() : ProductSpecification.having(
                (path, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.between(criteriaBuilder.avg(path.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)), lower, upper));
    }

    // God forgive me for those sql sins, amen
    public static ProductSpecification tags(Set<String> tags) {
        return tags == null || tags.isEmpty() ? ProductSpecification.empty() : ProductSpecification.where((root, criteriaQuery, criteriaBuilder) -> {
            Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
            Root<Product> subqueryRoot = subquery.from(Product.class);

            subquery.select(criteriaBuilder.count(subqueryRoot.get(Product_.ID)))
                    .where(
                            criteriaBuilder.equal(subqueryRoot.get(Product_.ID), root.get(Product_.ID)),
                            criteriaBuilder.in(subqueryRoot.get(Product_.TAGS).get(Tag_.NAME)).value(tags)
                    );

            return criteriaBuilder.equal(subquery, tags.size());
        });
    }

    public static ProductSpecification category(String category) {
        return category == null ? ProductSpecification.empty() : ProductSpecification.where(
                (path, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(criteriaBuilder.upper(path.get(Product_.CATEGORY).get(Category_.NAME)), category.toUpperCase()));
    }

}
