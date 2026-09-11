package com.gft.inditex.service.exception;

/**
 * Excepción general provocada por operaciones dentro del servicio
 */
public class InditexException extends Exception {
    public InditexException(String message) {
        super(message);
    }
}
