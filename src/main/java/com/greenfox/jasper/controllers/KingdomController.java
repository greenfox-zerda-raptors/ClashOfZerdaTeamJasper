package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.dto.LocationDto;
import com.greenfox.jasper.exception.notfound.KingdomNotFoundException;
import com.greenfox.jasper.security.JwtUser;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.KingdomServices;
import com.greenfox.jasper.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private ResourceServices resourceServices;

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<KingdomDto> getKingdom(@AuthenticationPrincipal JwtUser currentUser) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        resourceServices.calculateResource(kingdomId);
        Kingdom kingdom = kingdomServices.findOneKingdom(kingdomId);
        if (kingdom == null) {
            throw new KingdomNotFoundException(kingdomId);
        }
        KingdomDto result = dtoServices.convertKingdomToDTO(kingdom);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/location", method = RequestMethod.PUT)
    public ResponseEntity newLocation(@AuthenticationPrincipal JwtUser currentUser, @RequestBody LocationDto locationDto){
        kingdomServices.updateLocation(currentUser, locationDto);
        return ResponseEntity.status(HttpStatus.OK).body("Kingdom moved");
    }

}
