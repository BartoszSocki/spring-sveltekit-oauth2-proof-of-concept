package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ProductInventory extends WithCreationAndUpdateTimestamp {

    private Integer quantity;
    private Integer productsBought;

}
