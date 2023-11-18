package com.sockib.springresourceserver.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionInput {

    private List<TransactionProductInput> products;
    private AddressInput address;

}
