package com.sockib.springresourceserver.model.exception;

public class PageSizeTooLargeException extends RuntimeException {

    public PageSizeTooLargeException(String message) {
        super(message);
    }

}
