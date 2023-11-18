package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import com.sockib.springresourceserver.model.embeddable.Money;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@NamedEntityGraph(name = "product[owner]",
        attributeNodes = @NamedAttributeNode("owner")
)
@NamedEntityGraph(name = "product[category]",
        attributeNodes = @NamedAttributeNode("category")
)
@NamedEntityGraph(name = "product[inventory]",
        attributeNodes = @NamedAttributeNode("inventory")
)
@NamedEntityGraph(name = "product[ForDisplay]",
        attributeNodes = {
                @NamedAttributeNode("owner"),
                @NamedAttributeNode("category"),
                @NamedAttributeNode("inventory")
        }
)
@NamedEntityGraph(name = "product[all]",
        attributeNodes = {
                @NamedAttributeNode("owner"),
                @NamedAttributeNode("category"),
                @NamedAttributeNode("inventory"),
                @NamedAttributeNode("tags"),
        }
)
@Entity(name = "`product_catalog`")
public class Product extends WithCreationAndUpdateTimestamp {

    private String name;

    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private User owner;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private ProductInventory inventory;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_catalog_tag",
            joinColumns = @JoinColumn(name = "product_catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductReview> productReviews;

    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private String description;
    private String imageUrl;

//    @Transient
    private ProductScore productScore;

}
