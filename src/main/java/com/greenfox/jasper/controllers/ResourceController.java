package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.dto.ResourceResponse;
import com.greenfox.jasper.security.JwtUser;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/kingdom/resources", method = RequestMethod.GET)
public class ResourceController {

    @Autowired
    private ResourceServices resourceServices;

    @Autowired
    private DTOServices DTOServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ResourceResponse> getResources(@AuthenticationPrincipal JwtUser currentUser) {
        long kingdomId = currentUser.getId();
        resourceServices.calculateResource((int) kingdomId);
        List<Resource> resourceList = resourceServices.findAllResourcesByKingdomId((int) kingdomId);
        if (resourceList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ResourceResponse result = new ResourceResponse(
                dtoServices.convertResourcesListToDTO(resourceServices.findAllResourcesByKingdomId((int) kingdomId)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResponseEntity<ResourceResponse> getResourceBuildingByType(@AuthenticationPrincipal JwtUser currentUser, @PathVariable String type) {
        long kingdomId = currentUser.getId();
        resourceServices.calculateResource((int) kingdomId);
        Resource resourceList = resourceServices.findAllResourcesByKingdomIdAndType(kingdomId, type);
        List<Building> buildingList;
        if (type.equals("food")) {
            buildingList = resourceServices.findAllBuildingByKingdomIdAndByType((int) kingdomId, "farm");
        } else if (type.equals("gold")) {
            buildingList = resourceServices.findAllBuildingByKingdomIdAndByType((int) kingdomId, "mine");
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (resourceList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResourceResponse result = new ResourceResponse(DTOServices.convertResourceWithBuildingsDto(resourceList, buildingList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}