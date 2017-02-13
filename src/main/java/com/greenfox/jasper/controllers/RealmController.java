package com.greenfox.jasper.controllers;

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

    @RequestMapping(value = "")
    public ResponseEntity availableRealms(){
        List<Kingdom> allKingdoms = kingdomServices.findAll();
        KingdomListResponse result = new KingdomListResponse(dtoServices.convertKingdomListToDTO(allKingdoms));
        if (result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no kingdoms in this realm");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
