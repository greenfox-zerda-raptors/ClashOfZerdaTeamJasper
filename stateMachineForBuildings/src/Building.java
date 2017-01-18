/**
 * Created by almasics on 2017.01.18..
 */

public class Building {

    protected long constructionTime = 20000;
    protected int buildingLevel = 0;
    protected BuildingState buildingState;


    public Building() {
        this.buildingState = new Idle();
    }

    public Building(long constructionTime, int buildingLevel, BuildingState buildingState) {
        this.constructionTime = constructionTime;
        this.buildingLevel = buildingLevel;
        this.buildingState = buildingState;
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

}
