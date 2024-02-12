package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.value.Money;
import com.sockib.springresourceserver.model.value.ProductInventory;
import com.sockib.springresourceserver.model.value.ProductScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@NamedEntityGraph(name = "product[category]",
        attributeNodes = @NamedAttributeNode("category")
)
@NamedEntityGraph(name = "product[inventory]",
        attributeNodes = @NamedAttributeNode("inventory")
)
@NamedEntityGraph(name = "product[ForDisplay]",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("inventory")
        }
)
@NamedEntityGraph(name = "product[all]",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("inventory"),
                @NamedAttributeNode("tags"),
        }
)
@Table(name = "`product_catalog`")
@Entity
public class Product extends WithCreationAndUpdateTimestamp {

    private Boolean isDeleted;

    private String name;

    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @ToString.Exclude
    private ProductInventory inventory;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_catalog_tag",
            joinColumns = @JoinColumn(name = "product_catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
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

    @Transient
    private ProductScore productScore;

}
