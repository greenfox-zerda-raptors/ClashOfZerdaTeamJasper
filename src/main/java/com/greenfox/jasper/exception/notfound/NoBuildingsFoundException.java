package com.greenfox.jasper.exception.notfound;

import com.greenfox.jasper.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoBuildingsFoundException extends CustomException {
    public NoBuildingsFoundException(long kingdomId) {
        super("No buildings found for kingdom with id (" + kingdomId + ")");
    }
}
