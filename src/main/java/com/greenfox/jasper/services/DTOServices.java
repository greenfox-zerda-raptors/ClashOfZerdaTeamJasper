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
        KingdomDto kgdto = new KingdomDto();
        kgdto.setUser(convertUserToDto(kingdom.getUser()));
        kgdto.setBuildings(convertBuildingListToDTO(kingdom.getBuildings()));
        kgdto.setResources(convertResourcesListToDTO(kingdom.getResources()));
        kgdto.setTroops(convertTroopListToDTO(kingdom.getTroops()));
        return kgdto;
    }

    public List<BuildingDto> convertBuildingListToDTO(List<Building> buildingIterable){
        List<BuildingDto> result = new ArrayList<>();
        for(int i = 0; i <buildingIterable.size(); i++){
            result.add(modelMapper.map(buildingIterable.get(i), BuildingDto.class));
        }
        return result;
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
            result.add(modelMapper.map(resourcesList.get(i), ResourceDto.class));
        }
        return result;
    }

}
