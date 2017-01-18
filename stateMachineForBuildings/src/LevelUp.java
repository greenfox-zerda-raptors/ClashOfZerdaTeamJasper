/**
 * Created by almasics on 2017.01.18..
 */
public class LevelUp implements BuildingState {
    @Override
    public void switchState(Building building) {
        building.setUnitBuildTime(building.getUnitBuildTime() - 6000);
        System.out.println("Building has been updated");
        building.setBuildingState(new Idle());
    }
}
