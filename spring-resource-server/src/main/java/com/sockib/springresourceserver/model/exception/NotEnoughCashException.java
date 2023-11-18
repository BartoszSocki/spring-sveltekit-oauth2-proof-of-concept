package com.sockib.springresourceserver.model.exception;

public class NotEnoughCashException extends RuntimeException {
    public NotEnoughCashException(String message) {
        super(message);
    }
}
