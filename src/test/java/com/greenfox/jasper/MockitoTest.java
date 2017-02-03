package com.greenfox.jasper;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.KingdomRepo;
import com.greenfox.jasper.repos.TimedEventRepo;
import com.greenfox.jasper.services.EventServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by Zoltán on 2017.02.01..
 */

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class MockitoTest {

    @Mock
    private EventServices mockEventServices;

    @Mock
    private TimedEventRepo mockTimedEventRepo;

    @Mock
    private BuildingRepo mockBuildingRepo;

    @Mock
    private KingdomRepo mockKingdomRepo;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testForMockito(){

        when(mockTimedEventRepo.findAll()).thenReturn(new ArrayList<TimedEvent>(){{
            add(new TimedEvent(1, System.currentTimeMillis(), GameEvent.LEVELUP));}});

        Assert.assertEquals(1, mockTimedEventRepo.findAll().size());
    }
    @Test
    public void testMockCreation(){
        assertNotNull(mockEventServices);
        assertNotNull(mockBuildingRepo);
        assertNotNull(mockTimedEventRepo);
    }

    @Test
    public void testTwoMockito() {
        when(mockBuildingRepo.findOne(1l)).thenReturn(new Building("mine", new Kingdom("foobar", new User())));
        doCallRealMethod().when(mockEventServices).executeEvent(1l, GameEvent.LEVELUP);
        assertEquals(0, mockBuildingRepo.findOne(1l).getLevel());
    }


    @Test
    public void testwithnotmock(){
        List<Building> buildingList = new ArrayList<Building>(){{
            add(new Building());
            add(new Building());
            add(new Building());}};

        stub(mockBuildingRepo.findAll()).toReturn(buildingList);

        ArrayList<Building> testBuildingList = new ArrayList<>();
        for(Building building : mockBuildingRepo.findAll()){
            testBuildingList.add(building);
        }
        assertEquals(3, testBuildingList.size());
    }
}