package com.urianskui.studentweb.exceptions;

public class ApiException {

    private String message;
    private int status;

    public ApiException(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String toString() {
        return "{\n" +
                "message: " + message+ ",\n"+
                "status: " + status + "\n"+
                "}";
    }
}
