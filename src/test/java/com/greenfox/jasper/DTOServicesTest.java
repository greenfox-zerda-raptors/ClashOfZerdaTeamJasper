package com.greenfox.jasper;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.ResourceDto;
import com.greenfox.jasper.dto.TroopDto;
import com.greenfox.jasper.services.DTOServices;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zoltán on 2017.02.14..
 */

public class DTOServicesTest {
    private DTOServices dtoServices = new DTOServices();

    @Test
    public void BuildingConvertToDtoTest(){
        Kingdom kg = new Kingdom();
        Building testBuilding = new Building("mine", kg);
        testBuilding.setBuildingId(0);
        BuildingDto converted = dtoServices.convertBuildingToDTO(testBuilding);

        BuildingDto expected = new BuildingDto();
        expected.setHp(100);
        expected.setLevel(0);
        expected.setType("mine");
        expected.setId(testBuilding.getBuildingId());

        assertEquals(expected.toString(), converted.toString());
    }

    @Test
    public void BuildingListDtoConverterTest() {
        Kingdom kd = new Kingdom();
        Building buildingOne = new Building("mine", kd);
        Building buildingTwo = new Building("farm", kd);

        List<Building> buildingList = new ArrayList<>();
        buildingList.add(buildingOne);
        buildingList.add(buildingTwo);

        List<BuildingDto> convertedList = dtoServices.convertBuildingListToDTO(buildingList);

        List<BuildingDto> expectedList = new ArrayList<>();
        expectedList.add(dtoServices.convertBuildingToDTO(buildingOne));
        expectedList.add(dtoServices.convertBuildingToDTO(buildingTwo));

        assertEquals(expectedList.get(0).toString(), convertedList.get(0).toString());
        assertEquals(expectedList.get(1).toString(), convertedList.get(1).toString());

    }
        @Test
        public void convertTroopToDtoTest(){
            Troop testTroop = new Troop();
            TroopDto converted = dtoServices.convertTRoopToDTO(testTroop);

            TroopDto expected = new TroopDto();
            expected.setId(0);
            expected.setHp(20);
            expected.setLevel(0);
            expected.setAttack(10);
            expected.setDefense(5);

            assertEquals(expected.toString(), converted.toString());
        }

        @Test
    public void convertTroopListToDtoTest(){
            List<Troop> troopList = new ArrayList<>(
                    Arrays.asList(
                            new Troop(),
                            new Troop())
            );
            List<TroopDto> converted = dtoServices.convertTroopListToDTO(troopList);

            List<TroopDto> expected = new ArrayList<>(
                    Arrays.asList(
                            dtoServices.convertTRoopToDTO(troopList.get(0)),
                            dtoServices.convertTRoopToDTO(troopList.get(1))
                    ));
            assertEquals(expected.get(0).toString(), converted.get(0).toString());
            assertEquals(expected.size(), converted.size());
            assertEquals(expected.get(1).toString(), converted.get(1).toString());
        }
        @Test
        public void convertResourcesToDtoTest(){
            Resource testResource = new Resource("gold");
            List<Building> testBuildingList = new ArrayList<>(
                    Arrays.asList(new Building())
            );
            testResource.setBuildings(testBuildingList);

            ResourceDto converted = dtoServices.convertResourcesToDTO(testResource);

            ResourceDto expected = new ResourceDto();
            expected.setId(0);
            expected.setAmount(1);
            expected.setType("gold");

            assertEquals(expected.toString(), converted.toString());
        }
}
