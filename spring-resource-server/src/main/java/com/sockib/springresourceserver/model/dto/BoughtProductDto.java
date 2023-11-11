package com.sockib.springresourceserver.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoughtProductDto {

    private String name;
    private MoneyDto price;
    private Long ownerId;
    private String category;
    private String description;
    private String imageUrl;

}
