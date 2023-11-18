package com.sockib.springresourceserver.model.exception;

public class CurrencyNotSupportedException extends RuntimeException {
    public CurrencyNotSupportedException(String message) {
        super(message);
    }
}
