package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Troop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TroopRepo extends CrudRepository<Troop, Long> {
    public List<Troop> findAllByKingdom(Kingdom kingdom);
}
