package com.greenfox.jasper.repos.TimedEventRepos;

import com.greenfox.jasper.domain.TimedEvent.TrainTroopEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Zoltán on 2017.02.08..
 */
public interface TrainTroopEventRepo extends CrudRepository<TrainTroopEvent,Long>{
    List<TrainTroopEvent> findAllByKingdomIdOrderByExecutionTimeDesc(long buildingID);
}
