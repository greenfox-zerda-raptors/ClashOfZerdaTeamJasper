package com.greenfox.jasper.exception.notfound;

import com.greenfox.jasper.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoUsersFoundException extends CustomException {
    public NoUsersFoundException() {
        super("No users found");
    }
}
