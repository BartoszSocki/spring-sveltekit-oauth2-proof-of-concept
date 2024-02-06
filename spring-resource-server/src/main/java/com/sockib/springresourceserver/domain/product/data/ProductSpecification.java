package com.sockib.springresourceserver.domain.product.data;

import com.sockib.springresourceserver.model.value.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> nameLike(String name) {
        return name == null ? null : (path, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(path.get(Product_.NAME)), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
        Double lower = minPrice == null ? 0 : minPrice;
        Double upper = maxPrice == null ? Double.MAX_VALUE : maxPrice;

        return (path, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(path.get(Product_.PRICE).get("amount"), lower, upper);
    }

    public static Specification<Product> scoreBetween(Integer minScore, Integer maxScore) {
        Double lower = minScore == null ? ProductScore.MIN_SCORE : minScore;
        Double upper = maxScore == null ? ProductScore.MAX_SCORE : maxScore;

        return (path, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.between(criteriaBuilder.avg(path.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE)), lower, upper);
        };
    }

}
