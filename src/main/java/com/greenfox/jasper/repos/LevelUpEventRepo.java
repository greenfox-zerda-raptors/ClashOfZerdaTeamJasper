package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.TimedEvent.LevelUpEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface LevelUpEventRepo extends CrudRepository<LevelUpEvent, Long> {
}
