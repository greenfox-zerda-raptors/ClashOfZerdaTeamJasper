package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.dto.ResourceResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/resources", method = RequestMethod.GET)
public class ResourceController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ResourceResponse> getResources(@PathVariable int kingdomId) {
        List<Resource> resourceList = mainServices.findAllResourcesByKingdomId(kingdomId);
        if(resourceList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ResourceResponse result = new ResourceResponse(
                dtoServices.convertResourcesListToDTO(mainServices.findAllResourcesByKingdomId(kingdomId)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResponseEntity<ResourceResponse> getResourceBuildingFarm(@PathVariable int kingdomId, @PathVariable String type){
        List<Resource> resourceList = mainServices.findAllResourcesByKingdomIdAndType(kingdomId, type);
        List<Building> buildingList;
        if(type.equals("food")) {
            buildingList = mainServices.findAllBuildingByKingdomIdAndByType(kingdomId, "farm");
        }else if(type.equals("gold")){
            buildingList = mainServices.findAllBuildingByKingdomIdAndByType(kingdomId, "mine");
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(resourceList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResourceResponse result = new ResourceResponse(dtoServices.convertResourceWithBuildingsDto(resourceList.get(0), buildingList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}