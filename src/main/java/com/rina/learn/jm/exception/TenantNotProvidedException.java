package com.rina.learn.jm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TenantNotProvidedException extends RuntimeException {
    public TenantNotProvidedException(final String message) {
        super(message);
    }
}
