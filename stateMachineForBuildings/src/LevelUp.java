/**
 * Created by almasics on 2017.01.18..
 */
public class LevelUp implements BuildingState {

    @Override
    public void switchState(Building building) {
        System.out.println("Upgrading building");
        long currentTime = System.currentTimeMillis();
        if (currentTime > building.endUpgradeTime) {
            upgradeBuildingLevel(building);
            System.out.println("Building has been updated");
            building.setBuildingState(new Idle());
        }
        System.out.println("building has not been upgraded");
    }

    public void upgradeBuildingLevel(Building building) {
        building.setBuildingLevel(building.getBuildingLevel() + 1);
    }
}
