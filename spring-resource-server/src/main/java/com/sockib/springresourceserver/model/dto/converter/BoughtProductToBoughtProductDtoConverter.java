package com.sockib.springresourceserver.model.dto.converter;

import com.sockib.springresourceserver.model.dto.BoughtProductDto;
import com.sockib.springresourceserver.model.dto.MoneyDto;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.BoughtProduct;

public class BoughtProductToBoughtProductDtoConverter implements ToDtoConverter<BoughtProduct, BoughtProductDto> {

    private final static ToDtoConverter<Money, MoneyDto> moneyConverter = new MoneyToMoneyDtoConverter();

    @Override
    public BoughtProductDto convert(BoughtProduct boughtProduct) {
        var money = moneyConverter.convert(boughtProduct.getPrice());

        var boughtProductDto = new BoughtProductDto();
        boughtProductDto.setId(boughtProduct.getId());
        boughtProductDto.setName(boughtProduct.getName());
        boughtProductDto.setDescription(boughtProduct.getDescription());
        boughtProductDto.setCategory(boughtProduct.getCategory().getName());
        boughtProductDto.setImageUrl(boughtProduct.getImageUrl());
        boughtProductDto.setOwnerId(boughtProduct.getOwner().getId());
        boughtProductDto.setPrice(money);

        return boughtProductDto;
    }
}
