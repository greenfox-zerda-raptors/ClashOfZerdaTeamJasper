/**
 * Created by almasics on 2017.01.18..
 */
public class LevelUp implements BuildingState {

    @Override
    public void switchState(Building building) {
        System.out.println("Upgrading building");
        upgradeBuildingLevel(building);
        System.out.println("Building has been updated");
        building.setBuildingState(new Idle());
    }

    public void upgradeBuildingLevel(Building building) {
        building.setBuildingLevel(building.getBuildingLevel() + 1);
    }
}
