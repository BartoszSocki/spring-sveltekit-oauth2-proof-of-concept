package com.sockib.springresourceserver.service.transaction;

import com.sockib.springresourceserver.model.dto.input.AddressInput;

import java.util.List;

public interface TransactionService {

    void buyProducts(List<Long> productsIds, AddressInput address, String email);

}
