package com.greenfox.jasper.Services.Repositories;

import com.greenfox.jasper.Models.TimedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zoltán on 2017.01.22..
 */

public interface TimedEventRepo extends JpaRepository<TimedEvent, Long> {

    List<TimedEvent> findAll();

    List<TimedEvent> findAllByExecutionTimeLessThanEqual(long currentTime);

    List<TimedEvent> findAllByExecutionTimeBetween(long previousCheckTime, long currentTime);

}
