package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.dto.ResourceResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/resources", method = RequestMethod.GET)
public class ResourceController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResourceResponse getResources(@PathVariable int kingdomId, HttpServletResponse response) {
        List<Resource> resourceList = mainServices.findAllResourcesByKingdomId(kingdomId);
        if(resourceList == null) {
           response.setStatus(HttpServletResponse.SC_NOT_FOUND, "No such resources");
        }
            ResourceResponse result = new ResourceResponse(
                    dtoServices.convertResourcesListToDTO(mainServices.findAllResourcesByKingdomId(kingdomId)));
        return result;
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResourceResponse getResourceBuildingFarm(@PathVariable int kingdomId, @PathVariable String type, HttpServletResponse response) throws IOException{
        List<Resource> resourceList = mainServices.findAllResourcesByKingdomIdAndType(kingdomId, type);
       List<Building> buildingList = new ArrayList<>();
        if(type.equals("food")) {
            buildingList = mainServices.findAllBuildingByKingdomIdAndByType(kingdomId, "farm");
        }else if(type.equals("gold")){
            buildingList = mainServices.findAllBuildingByKingdomIdAndByType(kingdomId, "mine");
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND, "resources not found");
        }

        if(resourceList == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND, "resources not found");
        }
        ResourceResponse result = new ResourceResponse(dtoServices.convertResourceWithBuildingsDto(resourceList.get(0), buildingList));
        return result;
    }

}
