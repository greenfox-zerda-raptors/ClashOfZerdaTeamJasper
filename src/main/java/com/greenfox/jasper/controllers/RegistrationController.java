package com.greenfox.jasper.controllers;


import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.dto.UserDto;
import com.greenfox.jasper.services.RegistrationServices;
import com.greenfox.jasper.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/", method = RequestMethod.GET)
public class RegistrationController {

    @Autowired
    RegistrationServices registrationServices;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerNewUser (@RequestBody UserDto userDto){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (userServices.findeOneUserByName(userDto.getUsername()) == null
                && userServices.findOneUserByKingdom(userDto.getKingdomname()) == null){

            registrationServices.registerNewUser(new User(
                    userDto.getKingdomname(),
                    userDto.getUsername(),
                    encoder.encode(userDto.getPassword()),
                    userDto.getFirstname(),
                    userDto.getLastname(),
                    userDto.getEmail()));

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successful registration");
        }else if (userServices.findeOneUserByName(userDto.getUsername()) != null){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .body("Duplicate username");
        }else{
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .body("Duplicate kingdom");
        }


    }
}
