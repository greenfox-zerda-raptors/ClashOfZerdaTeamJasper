package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.TimedEvent.TimedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Zoltán on 2017.01.22..
 */

public interface TimedEventRepo extends JpaRepository<TimedEvent, Long> {

    List<TimedEvent> findAll();

    @Query("SELECT a FROM TimedEvent a WHERE a.wasExecuted = false and a.executionTime < :currentTime ")
    List<TimedEvent> findAllWaitingForExecution(@Param ("currentTime") long currentTime);

    List<TimedEvent> findAllByBuildingIdOrderByExecutionTimeDesc(long buildingID);


}
