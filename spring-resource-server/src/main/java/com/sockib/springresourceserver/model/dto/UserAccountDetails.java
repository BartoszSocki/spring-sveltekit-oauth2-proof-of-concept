package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import lombok.Value;

@Value
public class UserAccountDetails {

    String username;
    FiveStarScore score;

}
