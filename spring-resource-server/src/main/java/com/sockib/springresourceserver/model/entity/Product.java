package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

@Entity(name = "`product_catalog`")
@NamedEntityGraph(name = "product[owner]",
        attributeNodes = @NamedAttributeNode("owner")
)
@NamedEntityGraph(name = "product[category]",
        attributeNodes = @NamedAttributeNode("category")
)
public class Product extends WithCreationAndUpdateTimestamp {

    private String name;
    private Money price;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private ProductInventory inventory;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductReview> productReviews;

    private String description;
    private String imageUrl;

    private ProductScore productScore;

}
