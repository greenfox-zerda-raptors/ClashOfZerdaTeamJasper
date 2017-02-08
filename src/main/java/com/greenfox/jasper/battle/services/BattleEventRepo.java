package com.greenfox.jasper.battle.services;

import com.greenfox.jasper.battle.model.BattleEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface BattleEventRepo extends CrudRepository<BattleEvent, Long> {
}
