package com.exalt.villa.error;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException {
    public ApiBaseException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}