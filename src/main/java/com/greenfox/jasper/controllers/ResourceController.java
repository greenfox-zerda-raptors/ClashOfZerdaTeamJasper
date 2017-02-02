package com.greenfox.jasper.controllers;

import com.greenfox.jasper.DTO.ResourceResponse;
import com.greenfox.jasper.domain.Resource;
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
    public ResponseEntity<Object> getResources(@PathVariable int kingdomId) {
        List<Resource> resourceList = mainServices.findAllResourcesByKingdomId(kingdomId);
        if(resourceList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources not found for this kingdom");
        }
            ResourceResponse result = new ResourceResponse(
                    dtoServices.convertResourcesListToDTO(mainServices.findAllResourcesByKingdomId(kingdomId)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // TODO resolve error with custom query
//    @RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
//    public ResponseEntity<Object> getOneResource(@PathVariable int kingdomId, @PathVariable int resourceId) {
//        Resource resource = mainServices.findOneResource(resourceId);
//        List<Building> buildingList = mainServices.findAllBuildingByKingdomIdAndByType(kingdomId, resource.getType());
//
//        if(resource.getType() == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
//        }
//        ResourceResponse result = new ResourceResponse(
//                dtoServices.convertResourceWithBuildingsDto(resource, buildingList));
//
//        return new ResponseEntity<Object>(result, HttpStatus.OK);
//    }


}
