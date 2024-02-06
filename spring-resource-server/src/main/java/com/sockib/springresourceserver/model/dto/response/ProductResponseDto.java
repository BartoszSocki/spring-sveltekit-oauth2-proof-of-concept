package com.sockib.springresourceserver.model.dto.response;

import com.sockib.springresourceserver.model.value.ProductScore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private Boolean isDeleted;
    private Long ownerId;
    private String name;
    private MoneyResponseDto price;
    private Integer quantity;
    private String category;
    private List<String> tags;

    private String description;
    private String imageUrl;
    private ProductScore productScore;

}
