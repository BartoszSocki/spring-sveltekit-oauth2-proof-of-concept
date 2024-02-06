package com.sockib.springresourceserver.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    private List<OrderLineItemRequestDto> products;
    private AddressRequestDto address;

}
