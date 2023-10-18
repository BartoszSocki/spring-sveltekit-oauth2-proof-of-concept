package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Product extends Resource {

    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductDetails productDetails;

    private String name;
    private Integer quantity;

}
