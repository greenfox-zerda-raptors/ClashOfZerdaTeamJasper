package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.KingdomDto;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "/{kingdomId}", method = RequestMethod.GET)
    public KingdomDto getKingdom(@PathVariable int kingdomId, HttpServletResponse response) throws IOException {
        Kingdom kingdom = mainServices.findOneKingdom(kingdomId);

        if(kingdom == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        KingdomDto result = dtoServices.convertKingdomToDTO(kingdom);

        return result;

    }
}
