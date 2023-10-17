package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.Address;
import com.sockib.springresourceserver.model.valueobject.OrderStatus;
import com.sockib.springresourceserver.model.valueobject.Price;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;

//@Entity
public class Order extends Resource {

    // TODO: add required annotations
    private Product product;
    private Price price;
    private User seller;
    private User buyer;
    private Address address;
    private OrderStatus orderStatus;

}
