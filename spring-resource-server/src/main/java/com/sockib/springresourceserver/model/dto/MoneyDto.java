package com.sockib.springresourceserver.model.dto;


import com.sockib.springresourceserver.model.embeddable.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyDto {

    private Double amount;
    private String currency;

    public MoneyDto(Money money) {
        this.amount = money.getPrice();
        this.currency = money.getCurrency();
    }

}
