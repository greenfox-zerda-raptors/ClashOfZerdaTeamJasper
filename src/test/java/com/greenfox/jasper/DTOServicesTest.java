package com.greenfox.jasper;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.TroopDto;
import com.greenfox.jasper.services.DTOServices;
import org.junit.Test;

import java.util.ArrayList;
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
        expected.setId(0);

        assertEquals(expected.getLevel(), converted.getLevel());
        assertEquals(expected.getType(), converted.getType());
        assertEquals(expected.getHp(), converted.getHp());
        assertEquals(expected.getId(), converted.getId());
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

        assertEquals(expectedList.get(0).getId(), convertedList.get(0).getId());
        assertEquals(expectedList.get(0).getLevel(), convertedList.get(0).getLevel());
        assertEquals(expectedList.get(0).getHp(), convertedList.get(0).getHp());
        assertEquals(expectedList.get(0).getType(), convertedList.get(0).getType());
        assertEquals(expectedList.get(1).getId(), convertedList.get(1).getId());
        assertEquals(expectedList.get(1).getLevel(), convertedList.get(1).getLevel());
        assertEquals(expectedList.get(1).getHp(), convertedList.get(1).getHp());
        assertEquals(expectedList.get(1).getType(), convertedList.get(1).getType());

    }
        @Test
        public void convertTroopToDtoTest(){
            Troop testTroop = new Troop();
            TroopDto converted = dtoServices.convertTRoopToDTO(testTroop);

        }


}
