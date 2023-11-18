package com.sockib.springresourceserver.service.transaction;

import com.sockib.springresourceserver.model.dto.input.AddressInput;
import com.sockib.springresourceserver.model.dto.input.TransactionProductInput;

import java.util.List;

public interface TransactionService {

    void buyProducts(List<TransactionProductInput> productsIds, AddressInput address, String email);

}
