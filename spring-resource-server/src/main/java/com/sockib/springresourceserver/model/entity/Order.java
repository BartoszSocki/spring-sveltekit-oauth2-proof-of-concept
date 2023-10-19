package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.Address;
import com.sockib.springresourceserver.model.valueobject.OrderStatus;
import com.sockib.springresourceserver.model.valueobject.Price;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "`order`")
public class Order extends Resource {

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    private Price price;

    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;

    @ManyToOne(cascade = CascadeType.ALL)
    private User buyer;

    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
