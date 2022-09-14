package com.maula.ismatul.resto.exception;


import org.springframework.http.HttpStatus;

public class ForbiddenException extends ErrorException {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
