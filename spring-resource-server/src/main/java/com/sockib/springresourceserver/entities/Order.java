package com.sockib.springresourceserver.entities;

import com.sockib.springresourceserver.entities.embedable.Address;
import com.sockib.springresourceserver.entities.embedable.OrderStatus;
import com.sockib.springresourceserver.entities.embedable.Price;
import com.sockib.springresourceserver.entities.mappedsuperclasses.ResourceWithHistory;
import jakarta.persistence.Entity;

@Entity
public class Order extends ResourceWithHistory {

    // TODO: add required annotations
    private Product product;
    private Price price;
    private User seller;
    private User buyer;
    private Address address;
    private OrderStatus orderStatus;

}
