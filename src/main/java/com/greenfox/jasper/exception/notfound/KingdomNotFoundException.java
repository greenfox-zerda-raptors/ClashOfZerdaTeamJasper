package com.greenfox.jasper.exception.notfound;

import com.greenfox.jasper.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KingdomNotFoundException extends CustomException {

    public KingdomNotFoundException(long kingdomId) {
        super("Kingdom not found with id" + kingdomId);
    }
}
