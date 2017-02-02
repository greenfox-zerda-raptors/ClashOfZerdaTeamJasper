package com.greenfox.jasper.controllers;

import com.greenfox.jasper.DTO.KingdomDto;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "/{kingdomId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getKingdom(@PathVariable int kingdomId) {
        Kingdom kingdom = mainServices.findOneKingdom(kingdomId);

        if(kingdom == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("userId not found");
        }

        KingdomDto result = dtoServices.convertKingdomToDTO(kingdom);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
