package com.sockib.springresourceserver.service.order;

import com.sockib.springresourceserver.model.dto.input.AddressInput;
import com.sockib.springresourceserver.model.dto.input.OrderProductInput;

import java.util.List;

public interface OrderService {

    void buyProducts(List<OrderProductInput> productsIds, AddressInput address, String email);

}
