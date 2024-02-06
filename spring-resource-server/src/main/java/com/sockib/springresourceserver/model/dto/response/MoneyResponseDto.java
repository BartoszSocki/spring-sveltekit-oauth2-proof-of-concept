package com.sockib.springresourceserver.model.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyResponseDto {

    private Double amount;
    private String currency;

}
