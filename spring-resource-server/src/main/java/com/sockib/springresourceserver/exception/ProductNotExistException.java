package com.sockib.springresourceserver.exception;

public class ProductNotExistException extends RuntimeException {

    public ProductNotExistException(String message) {
        super(message);
    }

}
