package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.Address;
import com.sockib.springresourceserver.model.valueobject.OrderStatus;
import com.sockib.springresourceserver.model.valueobject.Price;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Order extends Resource {

    @ManyToOne
    private Product product;
    private Price price;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
