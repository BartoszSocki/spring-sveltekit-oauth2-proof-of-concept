package com.sockib.springresourceserver.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoughtProductResponseDto {

    private Long id;
    private Long productId;
    private String name;
    private MoneyResponseDto price;
    private Long ownerId;
    private String category;
    private LocalDate date;
    private String description;
    private String imageUrl;

}
