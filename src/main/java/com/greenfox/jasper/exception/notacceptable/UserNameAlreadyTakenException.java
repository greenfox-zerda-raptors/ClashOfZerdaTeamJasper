package com.greenfox.jasper.exception.notacceptable;

import com.greenfox.jasper.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class UserNameAlreadyTakenException extends CustomException {
    public UserNameAlreadyTakenException(String userName) {
        super("This username is currently taken (" + userName + ")");
    }
}
