package com.sockib.springresourceserver.domain.product.query;

import com.sockib.springresourceserver.model.entity.Category_;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.model.value.ProductScore;

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
    
//    public static Specification<Product> tags(Set<String> tags) {
//        System.out.println(tags);
//        return tags == null || tags.isEmpty() ? null : (path, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder.equal(criteriaBuilder.sum(
//                            criteriaBuilder.selectCase()
//                                    .when(criteriaBuilder.in(path.get(Product_.TAGS)).value(tags), 1)
//                                    .otherwise(0).as(Long.class)
//                    ), tags.size()
//            );
//        };
//    }
//
    public static ProductSpecification category(String category) {
        return category == null ? ProductSpecification.empty() : ProductSpecification.where(
                (path, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(criteriaBuilder.upper(path.get(Product_.CATEGORY).get(Category_.NAME)), category.toUpperCase()));
    }

}
