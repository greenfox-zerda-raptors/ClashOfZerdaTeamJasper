package com.greenfox.jasper.exception.badrequest;

import com.greenfox.jasper.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TroopOccupiedException extends CustomException {
    public TroopOccupiedException(long id) {
        super("This troop is with id = (" + id + ") is currently occupied!");
    }
}
