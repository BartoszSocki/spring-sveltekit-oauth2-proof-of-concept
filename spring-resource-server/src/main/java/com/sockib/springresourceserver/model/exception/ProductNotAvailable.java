package com.sockib.springresourceserver.model.exception;

public class ProductNotAvailable extends RuntimeException {

    public ProductNotAvailable(String message) {
        super(message);
    }

}
