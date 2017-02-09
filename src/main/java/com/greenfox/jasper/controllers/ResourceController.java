package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.dto.ResourceResponse;
import com.greenfox.jasper.services.DTOconverter;
import com.greenfox.jasper.services.ResourceServices;
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
    private ResourceServices resourceServices;

    @Autowired
    private DTOconverter DTOconverter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ResourceResponse> getResources(@PathVariable int kingdomId) {
        resourceServices.calculateResource(kingdomId);
        List<Resource> resourceList = resourceServices.findAllResourcesByKingdomId(kingdomId);
        if(resourceList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ResourceResponse result = new ResourceResponse(
                DTOconverter.convertResourcesListToDTO(resourceServices.findAllResourcesByKingdomId(kingdomId)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResponseEntity<ResourceResponse> getResourceBuildingByType(@PathVariable int kingdomId, @PathVariable String type){
        resourceServices.calculateResource(kingdomId);
        Resource resourceList = resourceServices.findAllResourcesByKingdomIdAndType(kingdomId, type);
        List<Building> buildingList;
        if(type.equals("food")) {
            buildingList = resourceServices.findAllBuildingByKingdomIdAndByType(kingdomId, "farm");
        }else if(type.equals("gold")){
            buildingList = resourceServices.findAllBuildingByKingdomIdAndByType(kingdomId, "mine");
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(resourceList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResourceResponse result = new ResourceResponse(DTOconverter.convertResourceWithBuildingsDto(resourceList, buildingList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}