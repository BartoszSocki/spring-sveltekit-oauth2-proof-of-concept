package com.sockib.springresourceserver.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoughtProductDto {

    private Long id;
    private String name;
    private MoneyDto price;
    private Long ownerId;
    private String category;
    private LocalDate date;
    private String description;
    private String imageUrl;

}
