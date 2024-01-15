package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private Boolean isDeleted;
    private Long ownerId;
    private String name;
    private MoneyDto price;
    private Integer quantity;
    private String category;
    private List<String> tags;

    private String description;
    private String imageUrl;
    private ProductScore productScore;

}
