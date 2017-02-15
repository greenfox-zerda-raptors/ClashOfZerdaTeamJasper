package com.greenfox.jasper.repos.TimedEventRepos;

import com.greenfox.jasper.domain.TimedEvent.UpgradeTroopEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Zoltán on 2017.02.08..
 */
public interface UpgradeTroopEventRepo extends CrudRepository<UpgradeTroopEvent,Long>{
    List<UpgradeTroopEvent> findAllByKingdomIdOrderByExecutionTimeDesc(long buildingID);
}
