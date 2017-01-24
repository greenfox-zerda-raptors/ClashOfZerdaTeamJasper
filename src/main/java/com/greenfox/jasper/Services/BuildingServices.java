package com.greenfox.jasper.Services;

import com.greenfox.jasper.Models.GameItem.Building;
import com.greenfox.jasper.Services.Repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Zoltán on 2017.01.20..
 */

@Component
public class BuildingServices{

    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingServices(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    public Building findOne(long id){
        return buildingRepository.findOne(id);
    }
}
