package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ProductStock extends Resource {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;

    private Integer quantity;

}
