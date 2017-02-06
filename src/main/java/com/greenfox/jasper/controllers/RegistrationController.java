package com.greenfox.jasper.controllers;


import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.dto.UserDto;
import com.greenfox.jasper.services.RegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", method = RequestMethod.GET)
public class RegistrationController {

    @Autowired
    RegistrationServices registrationServices;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> registerNewUser (@RequestBody UserDto userDto){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        registrationServices.registerNewUser(new User(
                userDto.getKingdomname(),
                userDto.getUsername(),
                encoder.encode(userDto.getPassword()),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getEmail()));

        return new ResponseEntity(HttpStatus.OK);
    }
}
