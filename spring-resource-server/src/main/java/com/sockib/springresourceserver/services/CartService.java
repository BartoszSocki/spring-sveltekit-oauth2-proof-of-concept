package com.sockib.springresourceserver.services;

import com.sockib.springresourceserver.model.dto.CartDetails;

public interface CartService {

    void buyCart(Long buyerId, CartDetails cartDetails);

}
