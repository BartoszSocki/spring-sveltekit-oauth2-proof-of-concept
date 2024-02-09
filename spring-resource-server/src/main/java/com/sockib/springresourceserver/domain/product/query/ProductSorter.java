package com.sockib.springresourceserver.domain.product.query;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.ProductReview_;
import com.sockib.springresourceserver.model.entity.Product_;
import com.sockib.springresourceserver.model.value.Money_;
import com.sockib.springresourceserver.core.util.Sorter;
import jakarta.persistence.criteria.Expression;

public class ProductSorter {

    public static Sorter<Product> price(boolean isAscending) {
        return (path, criteriaBuilder) -> {
            Expression<?> expr = path.get(Product_.PRICE).get(Money_.AMOUNT);
            return isAscending ? criteriaBuilder.asc(expr) : criteriaBuilder.desc(expr);
        };
    }

    public static Sorter<Product> score(boolean isAscending) {
        return (path, criteriaBuilder) -> {
            Expression<?> expr = criteriaBuilder.avg(path.get(Product_.PRODUCT_REVIEWS).get(ProductReview_.FIVE_STAR_SCORE));
            return isAscending ? criteriaBuilder.asc(expr) : criteriaBuilder.desc(expr);
        };
    }

}
