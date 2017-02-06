package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zoltán on 2017.02.02..
 */
@Service
public class DTOServices {

    ModelMapper modelMapper = new ModelMapper();

    public com.greenfox.jasper.dto.KingdomDto convertKingdomToDTO(Kingdom kingdom) {
        KingdomDto kingdomDto = new KingdomDto();
        kingdomDto.setUser(convertUserToDto(kingdom.getUser()));
        kingdomDto.setBuildings(convertBuildingListToDTO(kingdom.getBuildings()));
        kingdomDto.setResources(convertResourcesListToDTO(kingdom.getResources()));
        kingdomDto.setTroops(convertTroopListToDTO(kingdom.getTroops()));
        return kingdomDto;
    }

    public List<BuildingDto> convertBuildingListToDTO(List<Building> buildingList) {
        return buildingList.stream().map(this::convertBuildingToDTO).collect(Collectors.toList());
        // might not work with earlier versions of java
    }

    public BuildingDto convertBuildingToDTO(Building building) {
        return modelMapper.map(building, BuildingDto.class);
    }

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<TroopDto> convertTroopListToDTO(List<Troop> troopList) {
        return troopList.stream().map(this::convertTRoopToDTO).collect(Collectors.toList());
    }

    public TroopDto convertTRoopToDTO(Troop troop) {
        return modelMapper.map(troop, TroopDto.class);
    }

    public List<ResourceDto> convertResourcesListToDTO(List<Resource> resourcesList) {
        return resourcesList.stream().map(this::convertResourcesToDTO).collect(Collectors.toList());
    }

    public ResourceDto convertResourcesToDTO(Resource resource) {
        ResourceDto result = new ResourceDto();
        result.setId(resource.getId());
        result.setType(resource.getType());
        result.setAmount((int) resource.getAmount());
        result.setBuildings(convertBuildingListToDTO(resource.getBuildings()));
        return result;
    }

    public ResourceDto convertResourceWithBuildingsDto(Resource resource, List<Building> buildings) {
        ResourceDto result = new ResourceDto();
        result.setId(result.getId());
        result.setType(resource.getType());
        result.setAmount((int) resource.getAmount());
        result.setBuildings(convertBuildingListToDTO(buildings));
        return result;
    }

}
