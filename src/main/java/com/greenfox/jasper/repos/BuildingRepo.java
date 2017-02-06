package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuildingRepo extends CrudRepository<Building, Long>{
    List<Building> findAllByKingdom(Kingdom kingdom);

    List<Building> findAllBuildingByKingdomName(String kingdomName);

    @Query("SELECT a FROM Building a WHERE a.kingdom = :kingdom and a.type = :buildingtype")
    List<Building> findAllBuildingByKingdomAndType(@Param("kingdom") Kingdom kingdom, @Param("buildingtype") String type);

}
