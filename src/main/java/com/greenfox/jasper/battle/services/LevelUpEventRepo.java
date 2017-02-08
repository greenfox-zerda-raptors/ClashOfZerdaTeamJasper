package com.greenfox.jasper.battle.services;

import com.greenfox.jasper.battle.model.LevelUpEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface LevelUpEventRepo extends CrudRepository<LevelUpEvent, Long> {
}
