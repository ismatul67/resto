package com.maula.ismatul.resto.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ErrorException {
    public BadRequestException(String messageId) {
        super(messageId, HttpStatus.BAD_REQUEST);
    }
}
