package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import com.sockib.springresourceserver.model.value.Address;
import com.sockib.springresourceserver.model.value.Money;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
@Entity
public class Order extends WithCreationAndUpdateTimestamp {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    @ToString.Exclude
    private User buyer;

    //    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    @ToString.Exclude
    private Address address;

    private String orderStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @ToString.Exclude
    private List<LineItem> lineItems;

    public static Order bought(User buyer, List<LineItem> lineItems, Address address) {
        var order = new Order(buyer, address, "BOUGHT", lineItems);
        lineItems.forEach(lineItem -> lineItem.addToOrder(order));

        return order;
    }

    @Transient
    public Money calculateTotal() {
        return lineItems.stream()
                .map(lineItem -> lineItem.getPrice().multiply(lineItem.getQuantity()))
                .reduce(Money.USD(0.0), Money::add);
    }

    @Transient
    public void cancel() {
        this.orderStatus = "CANCEL";
    }

}
