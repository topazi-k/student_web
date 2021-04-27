package com.urianskui.studentweb.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiException {

    private String message;
    private int status;
    private LocalDateTime time;

    public ApiException(String message, int status) {
        this.message = message;
        this.status = status;
        time =LocalDateTime.now();
    }
}
