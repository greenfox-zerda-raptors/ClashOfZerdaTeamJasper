/**
 * Created by almasics on 2017.01.18..
 */
public class Building {

    protected long constructionTime;
    protected int buildingLevel;
    protected BuildingState buildingState;
    protected int baseCost;
    protected int buildingUpgradeCost;


    public Building() {
        this.buildingState = new Idle();
        this.buildingLevel = 0;
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
