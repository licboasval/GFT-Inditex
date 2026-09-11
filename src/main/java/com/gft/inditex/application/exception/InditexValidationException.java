package com.gft.inditex.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InditexValidationException extends InditexException {

    private final HttpStatus httpStatus;

    public InditexValidationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
