package com.sockib.springresourceserver.entities;

import com.sockib.springresourceserver.entities.embedable.Price;
import com.sockib.springresourceserver.entities.mappedsuperclasses.ResourceWithHistory;
import jakarta.persistence.Entity;

@Entity
public class Product extends ResourceWithHistory {

    // TODO: add required annotations
    private User seller;
    private ProductDetails productDetails;
    private String name;
    private Integer quantity;
    private Price price;

}
