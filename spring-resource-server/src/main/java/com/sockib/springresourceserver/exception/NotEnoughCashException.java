package com.sockib.springresourceserver.exception;

public class NotEnoughCashException extends RuntimeException {

    public NotEnoughCashException(String message) {
        super(message);
    }

}
