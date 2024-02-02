package com.sockib.springresourceserver.exception;

public class ProductNotAvailable extends RuntimeException {

    public ProductNotAvailable(String message) {
        super(message);
    }

}
