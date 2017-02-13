package com.greenfox.jasper.repos;

import com.greenfox.jasper.domain.TimedEvent.MainEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MainEventRepo extends CrudRepository <MainEvent, Long > {

    List<MainEvent> findAll();

    @Query("SELECT a FROM MainEvent a WHERE a.wasExecuted = false and a.executionTime < :currentTime ")
    List<MainEvent> findAllWaitingForExecution(@Param("currentTime") long currentTime);
}
