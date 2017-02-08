package com.greenfox.jasper.battle.services;

import com.greenfox.jasper.battle.model.UpgradeTroopEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface TroopUpgradeRepo extends CrudRepository<UpgradeTroopEvent, Long> {
}
