package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepo extends CrudRepository<Building, Long>{
    public Iterable<Building> findAllByKingdom(Kingdom kingdom);

}
