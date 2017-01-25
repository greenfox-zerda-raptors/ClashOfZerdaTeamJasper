package com.greenfox.jasper.Services.Repositories;

import com.greenfox.jasper.Models.TimedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Zoltán on 2017.01.22..
 */

public interface TimedEventRepo extends JpaRepository<TimedEvent, Long> {

    List<TimedEvent> findAll();

    List<TimedEvent> findAllByExecutionTimeLessThanEqual(long currentTime);//is there any need for this?

    List<TimedEvent> findAllByExecutionTimeBetween(long previousCheckTime, long currentTime);//same

    @Query("SELECT a FROM TimedEvent a where a.wasExecuted = false and a.executionTime < :currentTime ")
    List<TimedEvent> findAllWaitingForExecution(@Param ("currentTime") long currentTime);
}
