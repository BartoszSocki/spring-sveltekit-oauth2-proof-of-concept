package com.sockib.springresourceserver.model.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Embeddable
public class ProductScore {

    @Transient
    private Long numberOfReviews;

    @Transient
    private Double averageScore;

}
