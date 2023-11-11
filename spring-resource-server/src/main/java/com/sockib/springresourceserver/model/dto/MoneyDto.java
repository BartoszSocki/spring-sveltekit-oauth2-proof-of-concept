package com.sockib.springresourceserver.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyDto {

    private Double amount;
    private String currency;

}
