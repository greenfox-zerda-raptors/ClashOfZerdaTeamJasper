package com.greenfox.jasper.repos.TimedEventRepos;

import com.greenfox.jasper.domain.TimedEvent.UpgradeTroopEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface TroopUpgradeEventRepo extends CrudRepository<UpgradeTroopEvent, Long> {
}
