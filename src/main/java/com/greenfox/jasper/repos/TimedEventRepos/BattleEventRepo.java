package com.greenfox.jasper.repos.TimedEventRepos;

import com.greenfox.jasper.domain.TimedEvent.BattleEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface BattleEventRepo extends CrudRepository<BattleEvent, Long> {
}
