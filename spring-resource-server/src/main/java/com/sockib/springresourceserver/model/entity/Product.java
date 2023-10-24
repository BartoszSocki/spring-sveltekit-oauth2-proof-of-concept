package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity(name = "`product_catalog`")
public class Product extends WithCreationAndUpdateTimestamp {

    private String name;
    private Money price;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductInventory inventory;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;

    private String description;
    private String imageUrl;

}
