package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class BoughtProduct extends WithCreationAndUpdateTimestamp {

    private String name;

    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @ToString.Exclude
    private User owner;

    @ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    private String description;
    private String imageUrl;

}
