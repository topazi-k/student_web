package com.urianskui.studentweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalRequestParameterException.class)
    public ResponseEntity<ApiException> handle(IllegalRequestParameterException e) {
        ApiException apiException = new ApiException(e.getMessage(), 400);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        ApiException apiException = new ApiException("Something went wrong", 500);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
