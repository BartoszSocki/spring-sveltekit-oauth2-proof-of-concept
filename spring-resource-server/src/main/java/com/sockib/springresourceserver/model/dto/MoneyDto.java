package com.sockib.springresourceserver.model.dto;


import com.sockib.springresourceserver.model.embeddable.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyDto {

    private Double amount;
    private String currency;

    public MoneyDto(Money money) {
        this.amount = money.getAmount();
        this.currency = money.getCurrency();
    }

    public Money toMoney() {
        return new Money(amount, currency);
    }

}
