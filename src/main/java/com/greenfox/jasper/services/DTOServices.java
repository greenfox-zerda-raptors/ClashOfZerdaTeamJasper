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

    public KingdomDto convertKingdomToDTO(Kingdom kingdom) {
        KingdomDto kingdomDto = new KingdomDto();
        kingdom.setPosX(kingdom.getPosX());
        kingdom.setPosY(kingdom.getPosY());
        kingdomDto.setUser(convertSafeUserDto(kingdom.getUser()));
        kingdomDto.setBuildings(convertBuildingListToDTO(kingdom.getBuildings()));
        kingdomDto.setResources(convertResourcesListToDTO(kingdom.getResources()));
        kingdomDto.setTroops(convertTroopListToDTO(kingdom.getTroops()));
        return kingdomDto;
    }

    private SafeUserDto convertSafeUserDto(User user) {
       return modelMapper.map(user, SafeUserDto.class);
    }

    public List<BuildingDto> convertBuildingListToDTO(List<Building> buildingList) {
        return buildingList.stream().map(this::convertBuildingToDTO).collect(Collectors.toList());
    }

    public BuildingDto convertBuildingToDTO(Building building) {
        return modelMapper.map(building, BuildingDto.class);
    }

    public UserDto convertUserToDto(User user) {
        UserDto result = modelMapper.map(user, UserDto.class);
        result.setKingdomname(user.getKingdom().getName());
        return result;
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
        result.setId(resource.getId());
        result.setType(resource.getType());
        result.setAmount((int) resource.getAmount());
        result.setBuildings(convertBuildingListToDTO(buildings));
        return result;
    }

    public List<KingdomDto> convertKingdomListToDTO(List<Kingdom> kingdomList){
        return kingdomList.stream().map(this::convertKingdomToDTO).collect(Collectors.toList());
    }

    public void takeRegisterDTO() {

    }

    public BattleResponseDto convertBattleToDto(WarResult war) {

        BattleResponseDto result = new BattleResponseDto();

        result.setAttackerId(war.getAttackerId());
        result.setAttackerDamageDone(war.getAttackerDamageDone());
        result.setAttackerTroopIds(war.getAttackerTroopIds());
        result.setLostAttackerTroopIds(war.getLostAttackerTroopIds());

        result.setDefenderId(war.getDefenderId());
        result.setDefenderDamageDone(war.getDefenderDamageDone());
        result.setDefenderTroopIds(war.getDefenderTroopIds());
        result.setLostDefenderTroopIds(war.getLostDefenderTroopIds());
        result.setDefenderLostBuildingIds(war.getDefenderLostBuildingIds());

        return result;
    }

    public List<UserWithPointsDto> convertUserListToLeaderboard(List<User> users){
        return users.stream().map(this::convertUserToUserLeaderboard).collect(Collectors.toList());
    }

    private UserWithPointsDto convertUserToUserLeaderboard(User user) {
        UserWithPointsDto result = modelMapper.map(user, UserWithPointsDto.class);
        return result;
    }

}
