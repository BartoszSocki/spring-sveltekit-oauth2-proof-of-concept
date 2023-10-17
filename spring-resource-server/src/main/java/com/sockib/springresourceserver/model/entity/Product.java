package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.Price;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;

//@Entity
public class Product extends Resource {

    // TODO: add required annotations
    private User seller;
    private ProductDetails productDetails;
    private String name;
    private Integer quantity;
    private Price price;

}
