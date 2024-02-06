package com.sockib.springresourceserver.model.value;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Embeddable
public class FiveStarScore {

    private Integer fiveStarScore;

}
