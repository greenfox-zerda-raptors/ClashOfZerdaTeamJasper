package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "/{kingdomId}", method = RequestMethod.GET)
    public ResponseEntity<KingdomDto> getKingdom(@PathVariable int kingdomId, HttpServletResponse response) {
        Kingdom kingdom = mainServices.findOneKingdom(kingdomId);

        if(kingdom == null) {
            return new ResponseEntity(new CustomError("CustomError", 45l), HttpStatus.NOT_FOUND);
        }
      
        KingdomDto result = dtoServices.convertKingdomToDTO(kingdom);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
