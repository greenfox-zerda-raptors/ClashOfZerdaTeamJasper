package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.dto.KingdomListResponse;
import com.greenfox.jasper.dto.UserWithPointsDto;
import com.greenfox.jasper.exception.NoKingdomsFoundException;
import com.greenfox.jasper.exception.NoUsersFoundException;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.KingdomServices;
import com.greenfox.jasper.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/realm")
public class RealmController {
    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private DTOServices dtoServices;

    // TODO preauthorize to ONLY admin
    @RequestMapping(value = "")
    public ResponseEntity<KingdomListResponse> availableRealms(){
        List<Kingdom> allKingdoms = kingdomServices.findAll();
        KingdomListResponse result = new KingdomListResponse(dtoServices.convertKingdomListToDTO(allKingdoms));
        if (result == null){
            throw new NoKingdomsFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
    public ResponseEntity<List<UserWithPointsDto>> leaderboardofKingdoms(){
        List<User> allUsersByPoints = userServices.findAllByScore();
        if(allUsersByPoints.size() == 0){
           throw new NoUsersFoundException();
        }

        List<UserWithPointsDto> result =  dtoServices.convertUserListToLeaderboard(allUsersByPoints);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
