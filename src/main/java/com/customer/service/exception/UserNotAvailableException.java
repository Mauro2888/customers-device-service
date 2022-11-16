package com.customer.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNotAvailableException extends RuntimeException {
    public UserNotAvailableException(String message) {
        super(message);
    }
}
