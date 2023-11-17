package com.sockib.springresourceserver.controllers.advice;

import com.sockib.springresourceserver.model.exception.UserAlreadyAddedReviewException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ReviewControllerAdvice {

    @ExceptionHandler(value = UserAlreadyAddedReviewException.class)
    protected ResponseEntity<Map<String, String>> handleUserAlreadyAddedReviewException(RuntimeException exception) {
        var error = Map.of("error", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
