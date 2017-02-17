package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.KingdomListResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.KingdomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/realm")
public class RealmController {
    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private DTOServices dtoServices;

    // TODO preauthorize to ONLY admin
    @RequestMapping(value = "")
    public ResponseEntity<KingdomListResponse> availableRealms(){
        List<Kingdom> allKingdoms = kingdomServices.findAll();
        KingdomListResponse result = new KingdomListResponse(dtoServices.convertKingdomListToDTO(allKingdoms));
        if (result == null){
            return new ResponseEntity(new CustomError("No kingdoms in this realm", 400), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
