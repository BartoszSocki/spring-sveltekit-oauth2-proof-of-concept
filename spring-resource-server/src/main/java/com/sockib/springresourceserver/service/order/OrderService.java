package com.sockib.springresourceserver.service.order;

import com.sockib.springresourceserver.model.dto.request.OrderRequestDto;

public interface OrderService {

    void buyProducts(OrderRequestDto orderRequestDto, String buyerEmail);

}
