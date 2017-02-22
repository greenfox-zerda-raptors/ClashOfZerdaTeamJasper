package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public User getUser(@AuthenticationPrincipal JwtUser currentUser) {
//        long userId = currentUser.getHttpStatusCode();
//        return userServices.findOneUser(userId);
//    }



    // TODO Frontend request / do we need to give back everything?
    @RequestMapping(value = "/search/username", method = RequestMethod.GET)
    public ResponseEntity<KingdomDto> userSearch(@RequestParam("username") String username) {
        KingdomDto result = userServices.returnKingdomByUsername(username);
        if (result == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // TODO leaderboard GET all users by points
}
