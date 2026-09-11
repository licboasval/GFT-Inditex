package com.gft.inditex.application.exception;

import org.springframework.http.HttpStatus;

public class InditexValidationException extends InditexException {

    private final HttpStatus httpStatus;

    public InditexValidationException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public InditexValidationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
