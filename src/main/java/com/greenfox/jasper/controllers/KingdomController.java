package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.services.DtoConverter;
import com.greenfox.jasper.services.KingdomServices;
import com.greenfox.jasper.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private ResourceServices resourceServices;

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private DtoConverter DtoConverter;

    @RequestMapping(value = "/{kingdomId}", method = RequestMethod.GET)
    public ResponseEntity<KingdomDto> getKingdom(@PathVariable long kingdomId, HttpServletResponse response) {
        resourceServices.calculateResource(kingdomId);
        Kingdom kingdom = kingdomServices.findOneKingdom(kingdomId);
        if(kingdom == null) {
            return new ResponseEntity(new CustomError("Kingdom not found", 404), HttpStatus.NOT_FOUND);
        }
      
        KingdomDto result = DtoConverter.convertKingdomToDTO(kingdom);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
