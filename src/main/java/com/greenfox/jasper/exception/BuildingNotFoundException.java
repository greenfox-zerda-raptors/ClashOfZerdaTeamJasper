package com.greenfox.jasper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Zoltán on 2017.02.22..
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BuildingNotFoundException extends CustomException{
    public BuildingNotFoundException(long id) {
        super("No building with such id, (id = " + id + ")");
    }
}
