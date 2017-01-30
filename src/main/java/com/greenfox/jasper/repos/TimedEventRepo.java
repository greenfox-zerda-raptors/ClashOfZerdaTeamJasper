package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.TimedEvent;
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

    @Query("SELECT a FROM TimedEvent a WHERE a.wasExecuted = false and a.executionTime < :currentTime ")
    List<TimedEvent> findAllWaitingForExecution(@Param ("currentTime") long currentTime);

//    @Query("SELECT a FROM TimedEvent a WHERE a.wasExecuted = false and a.buildingId = :buildingId and (a.event = 0 or a.event =  1)")
//    List<TimedEvent> findAllEventForABuilding(@Param ("buildingId") long buildingId);

    List<TimedEvent> findAllByBuildingIdOrderByExecutionTime(long buildingID);
}
