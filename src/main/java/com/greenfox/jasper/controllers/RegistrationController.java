package com.greenfox.jasper.controllers;


import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.dto.UserDto;
import com.greenfox.jasper.exception.notacceptable.EmailAddressAlreadyTakenException;
import com.greenfox.jasper.exception.notacceptable.KingdomNameAlreadyTakenException;
import com.greenfox.jasper.exception.notacceptable.PasswordTooShortException;
import com.greenfox.jasper.exception.notacceptable.UserNameAlreadyTakenException;
import com.greenfox.jasper.services.KingdomServices;
import com.greenfox.jasper.services.RegistrationServices;
import com.greenfox.jasper.services.UserServices;
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
    BCryptPasswordEncoder encoder;

    @Autowired
    RegistrationServices registrationServices;

    @Autowired
    UserServices userServices;

    @Autowired
    KingdomServices kingdomServices;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerNewUser(@RequestBody UserDto userDto) {

        if (userServices.findeOneUserByName(userDto.getUsername()) == null
                && kingdomServices.findKingdomByName(userDto.getKingdomname()) == null
                && userServices.findOneUserByEmail(userDto.getEmail()) == null) {

            registrationServices.registerNewUser(new User(
                    userDto.getKingdomname(),
                    userDto.getUsername(),
                    encoder.encode(userDto.getPassword()),
                    userDto.getFirstname(),
                    userDto.getLastname(),
                    userDto.getEmail()));
            //TODO new error dto to send Json back
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successful registration");
        } else if (userServices.findeOneUserByName(userDto.getUsername()) != null) {
                throw new UserNameAlreadyTakenException(userDto.getUsername());
        } else if (userServices.findOneUserByEmail(userDto.getEmail()) != null) {
            throw new EmailAddressAlreadyTakenException(userDto.getEmail());
        } else if(userDto.getPassword().length() <= 4) {
            throw new PasswordTooShortException();
        } else if (kingdomServices.findKingdomByName(userDto.getKingdomname()) != null){
           throw new KingdomNameAlreadyTakenException(userDto.getKingdomname());
        }else {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Sth went wrong");
        }


    }
}
