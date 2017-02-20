package com.greenfox.jasper;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.KingdomRepo;
import com.greenfox.jasper.services.BuildingServices;
import com.greenfox.jasper.services.KingdomServices;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Created by Zoltán on 2017.02.15..
 */
public class BuildingServicesTest{

    @InjectMocks
    private BuildingServices buildingServices;

    @InjectMocks
    private KingdomServices kingdomServices;

    @Mock
    private KingdomRepo kingdomRepo;

    @Mock
    private BuildingRepo buildingRepo;

    private final List<Building> testBuildingList = new ArrayList<>(
            Arrays.asList(
                    new Building("townhall"), new Building("mine"), new Building("farm"), new Building("barrack")
            ));

    @Test
    public void findOneBuildingTest(){
        initMocks(this);
        when(buildingRepo.findOne(10L)).thenReturn(new Building("mine"));
        Building testBuilding = buildingServices.findOneBuilding(10);
        assertNotNull(testBuilding);
        assertEquals(0, testBuilding.getLevel());
        assertEquals("mine", testBuilding.getType());
    }
    @Test (expected = NullPointerException.class)
    public void findOneBuildingWithNegativeId(){
        buildingServices.findOneBuilding(-10);
        fail();
    }

    @Test
    public void findAllBuildingsTest(){
        initMocks(this);
        when(buildingRepo.findAll()).thenReturn(testBuildingList);
        List<Building> obtainedList = buildingServices.findAllBuildings();
        assertNotNull(obtainedList);
        assertEquals("mine", obtainedList.get(1).getType());
        assertEquals(4, obtainedList.size());
        assertEquals("farm", obtainedList.get(2).getType());
        assertEquals(0, obtainedList.get(3).getLevel());

    }
}

