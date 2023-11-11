package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.model.dto.MoneyDto;
import com.sockib.springresourceserver.model.embeddable.Money;

public class MoneyToMoneyDtoConverter implements ToDtoConverter<Money, MoneyDto> {
    @Override
    public MoneyDto convert(Money money) {
        var moneyDto = new MoneyDto();
        moneyDto.setAmount(money.getAmount());
        moneyDto.setCurrency(money.getCurrency());

        return moneyDto;
    }
}
