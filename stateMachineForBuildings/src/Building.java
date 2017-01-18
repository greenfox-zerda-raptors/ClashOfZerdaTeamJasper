/**
 * Created by almasics on 2017.01.18..
 */
public class Building {

    private long unitBuildTime = 10000;
    private long constructionTime = 20000;
    private int buildingLevel = 0;
    private BuildingState buildingState;


    public Building() {
        this.buildingState = new Idle();
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

    public long getUnitBuildTime() {
        return unitBuildTime;
    }

    public void setUnitBuildTime(long unitBuildTime) {
        this.unitBuildTime = unitBuildTime;
    }
}
