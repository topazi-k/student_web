package com.urianskui.studentweb.exceptions;

import com.urianskui.studentweb.controller.StudentController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = StudentController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalRequestParameterException.class})
    public ResponseEntity<ApiException> handle(IllegalRequestParameterException e, WebRequest rq) {
        ApiException apiException = new ApiException(e.getMessage(), 400);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object>  handle(Exception ex, WebRequest request) {
        String apiException = new ApiException("Something went wrong", 500).toString();


        return handleExceptionInternal(ex, apiException,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
