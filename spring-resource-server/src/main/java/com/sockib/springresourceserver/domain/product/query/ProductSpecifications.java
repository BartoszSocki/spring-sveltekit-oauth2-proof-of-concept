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

    public static ProductSpecification tags(Set<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return ProductSpecification.empty();
        }

        ProductSpecification specification = ProductSpecification.empty();
        specification.setHavingSpecification((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.countDistinct(root.get(Product_.TAGS).get(Tag_.NAME)), tags.size()));

        specification.setWhereSpecification((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.in(root.get(Product_.TAGS).get(Tag_.NAME)).value(tags));

        return specification;
    }

    public static ProductSpecification category(String category) {
        return category == null ? ProductSpecification.empty() : ProductSpecification.where(
                (path, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(criteriaBuilder.upper(path.get(Product_.CATEGORY).get(Category_.NAME)), category.toUpperCase()));
    }

    public static ProductSpecification deleted(boolean deleted) {
        return ProductSpecification.where((path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(path.get(Product_.IS_DELETED), deleted));
    }

}
