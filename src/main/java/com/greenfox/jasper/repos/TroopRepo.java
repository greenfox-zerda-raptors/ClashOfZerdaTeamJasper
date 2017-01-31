package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Troop;
import org.springframework.data.repository.CrudRepository;

public interface TroopRepo extends CrudRepository<Troop, Long> {
    public Iterable<Troop> findAllByKingdom(Kingdom kingdom);
}
