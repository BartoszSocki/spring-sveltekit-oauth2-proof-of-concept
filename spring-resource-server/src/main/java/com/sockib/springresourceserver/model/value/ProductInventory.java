package com.sockib.springresourceserver.model.value;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Embeddable
public class ProductInventory {

    private Integer quantity;
    private Integer productsBought = 0;

    public ProductInventory(Integer quantity) {
        this.quantity = quantity;
        this.productsBought = 0;
    }

}
