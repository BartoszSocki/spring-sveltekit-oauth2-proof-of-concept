package com.sockib.springresourceserver.model.exception;

public class UserAlreadyAddedReviewException extends RuntimeException {

    public UserAlreadyAddedReviewException(String message) {
        super(message);
    }

}
