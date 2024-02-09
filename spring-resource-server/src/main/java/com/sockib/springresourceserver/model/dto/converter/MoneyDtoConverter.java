package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.core.util.DtoConverter;
import com.sockib.springresourceserver.model.dto.response.MoneyResponseDto;
import com.sockib.springresourceserver.model.value.Money;

public class MoneyDtoConverter implements DtoConverter<Money, MoneyResponseDto> {

    @Override
    public MoneyResponseDto convert(Money money) {
        var moneyDto = new MoneyResponseDto();
        moneyDto.setAmount(money.getAmount());
        moneyDto.setCurrency(money.getCurrency());

        return moneyDto;
    }

}
