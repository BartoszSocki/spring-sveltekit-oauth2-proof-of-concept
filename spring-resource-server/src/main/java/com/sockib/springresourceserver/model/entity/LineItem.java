package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.value.Money;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bought_product")
public class LineItem extends WithCreationAndUpdateTimestamp {

    private String name;

    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    private String description;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_catalog_id")
    @ToString.Exclude
    private Product product;

    private Integer quantity;

    public LineItem(String name, Money price, Category category, String description, String imageUrl, Product product) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.product = product;
    }

    @Transient
    public void addToOrder(Order order) {
        this.order = order;
    }

}
