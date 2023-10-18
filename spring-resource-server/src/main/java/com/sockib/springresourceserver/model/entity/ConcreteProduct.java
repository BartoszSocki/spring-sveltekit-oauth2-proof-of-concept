package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import com.sockib.springresourceserver.model.valueobject.ConcreteProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@Entity
public class ConcreteProduct extends Resource {

    @OneToOne(cascade = CascadeType.ALL)
    private ProductDetails productDetails;

    @OneToMany(mappedBy = "concreteProduct")
    private Set<ProductReview> productReviews;

    @Enumerated(EnumType.STRING)
    private ConcreteProductStatus concreteProductStatus;

}
