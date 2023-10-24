package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.embeddable.ProductScore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private Long ownerId;
    private String name;
    private Money price;
    private Integer quantity;
    private String category;

    private String description;
    private String imageUrl;
    private ProductScore productScore;

}
