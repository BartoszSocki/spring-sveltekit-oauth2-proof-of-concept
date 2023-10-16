package com.sockib.springresourceserver.model.entities;

import com.sockib.springresourceserver.model.entities.embedable.Address;
import com.sockib.springresourceserver.model.entities.embedable.OrderStatus;
import com.sockib.springresourceserver.model.entities.embedable.Price;
import com.sockib.springresourceserver.model.entities.mappedsuperclasses.EntityWithHistory;
import jakarta.persistence.Entity;

@Entity
public class Order extends EntityWithHistory {

    // TODO: add required annotations
    private Product product;
    private Price price;
    private User seller;
    private User buyer;
    private Address address;
    private OrderStatus orderStatus;

}
