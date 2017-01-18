/**
 * Created by almasics on 2017.01.18..
 */

public class Building {


    protected BuildingState buildingState;
    protected int buildingLevel;

    protected long constructionTime;
    protected int baseCost;
    protected int buildingUpgradeCost;
    protected long buildingUpgradeTime;
    protected long endUpgradeTime;


    public Building() {
        this.buildingState = new Idle();
        this.buildingLevel = 1;
    }

    public BuildingState getBuildingState() {
        return buildingState;
    }

    public void setBuildingState(BuildingState buildingState) {
        this.buildingState = buildingState;
    }

    public void switchState() {
        this.buildingState.switchState(this);
    }


    public long getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(long constructionTime) {
        this.constructionTime = constructionTime;
    }

    public int getBuildingLevel() {
        return buildingLevel;
    }

    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getBuildingUpgradeCost() {
        return buildingUpgradeCost;
    }

    public void setBuildingUpgradeCost(int buildingUpgradeCost) {
        this.buildingUpgradeCost = buildingUpgradeCost;
    }

    public String buildingToString() {
        return String.format("building details: %d level", buildingLevel);
    }

    public long getBuildingUpgradeTime() {
        return buildingUpgradeTime;
    }

    public void setBuildingUpgradeTime(long buildingUpgradeTime) {
        this.buildingUpgradeTime = buildingUpgradeTime;
    }

    public long getEndUpgradeTime() {
        return endUpgradeTime;
    }

    public void setEndUpgradeTime(long endUpgradeTime) {
        this.endUpgradeTime = endUpgradeTime;
    }
}
