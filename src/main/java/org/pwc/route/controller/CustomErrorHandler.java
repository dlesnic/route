package org.pwc.route.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleBadRequest(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleNotFound(IllegalArgumentException e) {
        return new ResponseEntity<>("Route not found", HttpStatus.BAD_REQUEST);
    }

}
