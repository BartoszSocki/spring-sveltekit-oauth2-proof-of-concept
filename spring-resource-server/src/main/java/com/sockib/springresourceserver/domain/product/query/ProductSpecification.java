package com.sockib.springresourceserver.domain.product.query;

import com.sockib.springresourceserver.model.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class ProductSpecification {

    private static final Specification<Product> EMPTY = (path, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and();
    private Specification<Product> whereSpecification;
    private Specification<Product> havingSpecification;

    private ProductSpecification(Specification<Product> whereSpecification, Specification<Product> havingSpecification) {
        this.whereSpecification = whereSpecification;
        this.havingSpecification = havingSpecification;
    }

    public static ProductSpecification where(Specification<Product> whereSpecification) {
        return new ProductSpecification(whereSpecification, EMPTY);
    }

    public static ProductSpecification having(Specification<Product> havingSpecification) {
        return new ProductSpecification(EMPTY, havingSpecification);
    }

    public static ProductSpecification empty() {
        return new ProductSpecification(EMPTY, EMPTY);
    }

    public ProductSpecification and(ProductSpecification productSpecification) {
        if (productSpecification == null) {
            return new ProductSpecification(this.whereSpecification, this.havingSpecification);
        }

        Specification<Product> whereSpecification = productSpecification.getWhereSpecification() == EMPTY ? null : productSpecification.getWhereSpecification();
        Specification<Product> havingSpecification = productSpecification.getHavingSpecification() == EMPTY ? null : productSpecification.getHavingSpecification();

        return new ProductSpecification(
                this.whereSpecification.and(whereSpecification),
                this.havingSpecification.and(havingSpecification)
        );
    }

}
