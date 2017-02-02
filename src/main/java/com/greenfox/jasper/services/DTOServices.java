package com.greenfox.jasper.services;

import com.greenfox.jasper.DTO.*;
import com.greenfox.jasper.domain.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoltán on 2017.02.02..
 */
@Service
public class DTOServices {

    ModelMapper modelMapper = new ModelMapper();

    public KingdomDto convertKingdomToDTO(Kingdom kingdom){
        KingdomDto kingdomDto = new KingdomDto();
        kingdomDto.setUser(convertUserToDto(kingdom.getUser()));
        kingdomDto.setBuildings(convertBuildingListToDTO(kingdom.getBuildings()));
        kingdomDto.setResources(convertResourcesListToDTO(kingdom.getResources()));
        kingdomDto.setTroops(convertTroopListToDTO(kingdom.getTroops()));
        return kingdomDto;
    }

    public List<BuildingDto> convertBuildingListToDTO(List<Building> buildingList){
        List<BuildingDto> result = new ArrayList<>();
        for(int i = 0; i <buildingList.size(); i++){
            result.add(convertBuildingToDTO(buildingList.get(i)));
        }
        return result;
    }
    public BuildingDto convertBuildingToDTO(Building building){
        return modelMapper.map(building, BuildingDto.class);
    }

    public UserDto convertUserToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public List<TroopDto> convertTroopListToDTO(List<Troop> troopList){
        List<TroopDto> result = new ArrayList<>();
        for(int i = 0; i <troopList.size(); i++){
            result.add(modelMapper.map(troopList.get(i), TroopDto.class));
        }
        return result;
    }
    public List<ResourceDto> convertResourcesListToDTO(List<Resource> resourcesList){
        List<ResourceDto> result = new ArrayList<>();
        for(int i = 0; i <resourcesList.size(); i++){
            result.add(convertResourcesToDTO(resourcesList.get(i)));
        }
        return result;
    }

    public ResourceDto convertResourcesToDTO(Resource resource) {
        ResourceDto result = new ResourceDto();
        result.setType(resource.getType());
        result.setAmount(resource.getAmount());
        result.setBuildings(convertBuildingListToDTO(resource.getBuildings()));
        return result;
    }

}
