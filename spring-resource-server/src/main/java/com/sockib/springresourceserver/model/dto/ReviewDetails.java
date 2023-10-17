package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import lombok.Value;

@Value
public class ReviewDetails {

    String title;
    String description;
    FiveStarScore fiveStarScore;

}
