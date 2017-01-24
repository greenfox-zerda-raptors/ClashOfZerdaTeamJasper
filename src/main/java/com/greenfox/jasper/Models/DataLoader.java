package com.greenfox.jasper.Models;

import com.greenfox.jasper.Services.Repositories.TimedEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Zoltán on 2017.01.24..
 */

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    TimedEventRepo timedEventRepo;

    @Override
    public void run(String... strings) throws Exception {
//        long currentTime = System.currentTimeMillis();
//            for(int kk =  0; kk < 50000; kk++){
//                TimedEvent testEvents = new TimedEvent(7l, false, (currentTime+60000 + 50*kk), GameEvent.LEVELUP);
//                timedEventRepo.save(testEvents);
//            }


    }
}
