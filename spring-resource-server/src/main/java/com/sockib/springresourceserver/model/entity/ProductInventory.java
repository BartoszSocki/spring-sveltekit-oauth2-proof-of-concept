package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class ProductInventory extends WithCreationAndUpdateTimestamp {

    private Integer quantity;
    private Integer productsBought = 0;

    @OneToOne(mappedBy = "inventory")
    private Product product;

    public ProductInventory(Integer quantity) {
        this.quantity = quantity;
    }

}
