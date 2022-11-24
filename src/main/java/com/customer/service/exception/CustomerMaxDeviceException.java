package com.customer.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Customer can have only 2 devices", code = HttpStatus.BAD_REQUEST)
public class CustomerMaxDeviceException extends RuntimeException {

    public CustomerMaxDeviceException() {
        super();
    }

    public CustomerMaxDeviceException(String message) {
        super(message);
    }

}
