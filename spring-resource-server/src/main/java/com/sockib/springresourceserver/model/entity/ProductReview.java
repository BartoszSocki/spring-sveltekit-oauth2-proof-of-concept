package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import com.sockib.springresourceserver.model.embeddable.FiveStarScore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ProductReview extends WithCreationAndUpdateTimestamp {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_catalog_id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    private User reviewer;

    private FiveStarScore fiveStarScore;
    private String review;

}
