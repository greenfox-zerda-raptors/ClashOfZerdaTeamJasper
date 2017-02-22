package com.greenfox.jasper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoKingdomsFoundException extends CustomException {
    public NoKingdomsFoundException() {
        super("No kingdoms found");
    }
}
