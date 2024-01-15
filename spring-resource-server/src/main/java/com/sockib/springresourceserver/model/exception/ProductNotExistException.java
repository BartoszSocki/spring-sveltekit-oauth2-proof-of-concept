package com.sockib.springresourceserver.model.exception;

public class ProductNotExistException extends RuntimeException {

    public ProductNotExistException(String message) {
        super(message);
    }

}
