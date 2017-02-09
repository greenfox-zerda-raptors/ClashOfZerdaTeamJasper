package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.BuildingResponse;
import com.greenfox.jasper.services.BuildingServices;
import com.greenfox.jasper.services.DtoConverter;
import com.greenfox.jasper.services.MainEventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/buildings")
public class BuildingController {

    @Autowired
    private BuildingServices buildingServices;

    @Autowired
    private MainEventServices mainEventServices;

    @Autowired
    private DtoConverter DtoConverter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BuildingResponse> getBuildings(@PathVariable long kingdomId) {
        List<Building> buildingList = buildingServices.findAllBuildingsByKingdomId(kingdomId);

        if(buildingList == null){
            return  new ResponseEntity(new CustomError("Buildings not found", 404), HttpStatus.NOT_FOUND);
        }
        BuildingResponse result = new BuildingResponse(DtoConverter.convertBuildingListToDTO(buildingList));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public ResponseEntity<BuildingDto> getOneBuilding(@PathVariable int buildingId) {
        BuildingDto result =
                DtoConverter.convertBuildingToDTO(buildingServices.findOneBuilding(buildingId));
        if(result == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/levelup/{buildingId}", method = RequestMethod.GET)
    public void levelUpBuildingById(@PathVariable long buildingId) throws IOException {
        mainEventServices.addNewLevelUpEvent(buildingId);
    }

    @RequestMapping(value = "/newbuilding/{type}", method = RequestMethod.GET)
    public void addNewBuilding(@PathVariable long kingdomId , @PathVariable String type) throws IOException{
        buildingServices.addNewBuilding(kingdomId, type);
    }
}