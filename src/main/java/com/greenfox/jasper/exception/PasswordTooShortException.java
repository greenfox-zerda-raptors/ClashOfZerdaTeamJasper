package com.greenfox.jasper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class PasswordTooShortException extends CustomException {
    public PasswordTooShortException() {
        super("Password is too short!");
    }
}
