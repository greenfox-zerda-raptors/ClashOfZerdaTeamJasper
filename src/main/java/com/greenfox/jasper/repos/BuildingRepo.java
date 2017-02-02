package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingRepo extends CrudRepository<Building, Long>{
    List<Building> findAllByKingdom(Kingdom kingdom);

    List<Building> findAllBuildingByKingdomName(String kingdomName);

    List<Building> findAllBuildingByKingdomIdAndByType(int resourceId, String type);
}
