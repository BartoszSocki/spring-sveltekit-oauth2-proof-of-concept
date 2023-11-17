package com.sockib.springresourceserver.controllers.advice;

import com.sockib.springresourceserver.model.exception.ProductReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReviewControllerAdvice {

    @ExceptionHandler(value = ProductReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleUserAlreadyAddedReviewException() {
        return ResponseEntity.notFound().build();
    }

}
