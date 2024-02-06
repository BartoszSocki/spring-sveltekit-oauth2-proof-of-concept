package com.sockib.springresourceserver.model.value;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.*;

@Data
@NoArgsConstructor
@Embeddable
public class ProductScore {

    public static final double MIN_SCORE = 1;
    public static final double MAX_SCORE = 5;

    @Transient
    private Long reviewsCount;

    @Transient
    private Double averageScore;

    public ProductScore(Long reviewsCount, Double averageScore) {
        super();
        this.reviewsCount = reviewsCount;
        this.averageScore = averageScore;
    }

}
