package com.greenfox.jasper.controllers;

import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/search/username", method = RequestMethod.GET)
    public ResponseEntity<KingdomDto> userSearch(@RequestParam("username") String username) {
        KingdomDto result = userServices.returnKingdomByUsername(username);
        if (result == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
