package com.greenfox.jasper.Services.Repositories;

import com.greenfox.jasper.Models.GameItem.Building;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Zoltán on 2017.01.20..
 */

public interface BuildingRepository extends CrudRepository<Building,Long > {

    List<Building> findAll();

    List<Building> findOneById(long id);
}
