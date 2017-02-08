package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.TimedEvent.UpgradeTroopEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Zoltán on 2017.02.07..
 */
public interface TroopUpgradeRepo extends CrudRepository<UpgradeTroopEvent, Long> {
}
