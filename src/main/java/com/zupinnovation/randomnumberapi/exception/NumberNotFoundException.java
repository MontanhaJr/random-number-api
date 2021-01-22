package com.zupinnovation.randomnumberapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NumberNotFoundException extends Exception {
    public NumberNotFoundException(Long number) {
        super("Number " + number + " was not found" );
    }
}
