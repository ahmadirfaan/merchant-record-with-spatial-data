package com.irfaan.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException{

    private HttpStatus status;

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
