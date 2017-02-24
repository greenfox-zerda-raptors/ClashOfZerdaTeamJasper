package com.greenfox.jasper.repos.TimedEventRepos;

import com.greenfox.jasper.domain.TimedEvent.LevelUpEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface LevelUpEventRepo extends CrudRepository<LevelUpEvent, Long> {

    List<LevelUpEvent> findAllByKingdomIdAndWasExecutedOrderByExecutionTimeDesc(long kingdomId, int wasExecuted);
}
