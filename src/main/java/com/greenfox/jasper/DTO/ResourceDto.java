package com.greenfox.jasper.DTO;

public class ResourceDto {
    private String type;
    private int amount;

    //TODO add buildings that produce this resource
//    private List<BuildingDto> buildings;


//    public List<BuildingDto> getBuildings() {
//        return buildings;
//    }
//
//    public void setBuildings(List<BuildingDto> buildings) {
//        this.buildings = buildings;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
