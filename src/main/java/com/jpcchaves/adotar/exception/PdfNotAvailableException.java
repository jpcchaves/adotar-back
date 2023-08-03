package com.jpcchaves.adotar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PdfNotAvailableException extends RuntimeException {
    public PdfNotAvailableException(String message) {
        super(message);
    }
}
