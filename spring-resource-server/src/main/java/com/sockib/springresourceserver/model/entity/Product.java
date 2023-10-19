package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import com.sockib.springresourceserver.model.valueobject.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@Entity
public class Product extends Resource {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;

    @OneToMany(mappedBy = "product")
    private Set<ProductReview> productReviews;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

}
