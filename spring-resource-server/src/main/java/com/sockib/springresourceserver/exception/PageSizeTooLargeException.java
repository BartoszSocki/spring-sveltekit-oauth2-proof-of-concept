package com.sockib.springresourceserver.exception;

public class PageSizeTooLargeException extends RuntimeException {

    public PageSizeTooLargeException(String message) {
        super(message);
    }

}
