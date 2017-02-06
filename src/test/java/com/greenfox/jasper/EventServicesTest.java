//package com.greenfox.jasper;
//
//import com.greenfox.jasper.domain.Building;
//import com.greenfox.jasper.domain.GameEvent;
//import com.greenfox.jasper.services.EventServices;
//import com.greenfox.jasper.services.MainServices;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by Zoltán on 2017.02.02..
// */
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class EventServicesTest {
//
//    @Autowired
//    EventServices eventServices;
//
//    @Autowired
//    MainServices mainServices;
//
//
//    @Test
//    public void testEventServiceExecuteEventMethod(){
//        Building testBuildingBefore = mainServices.findOneBuilding(11);
//        eventServices.executeEvent(11, GameEvent.LEVELUP);
//        Building testBuildingAfter = mainServices.findOneBuilding(11);
//        assertEquals(testBuildingBefore.getLevel()+1,testBuildingAfter.getLevel() );
//
//    }
//
//}
