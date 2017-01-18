/**
 * Created by almasics on 2017.01.18..
 */
public class Idle implements BuildingState {

    @Override
    public void switchState(Building building) {
        System.out.println("Building in idle mode");
        building.setBuildingState(new ProductionInProgress());
    }
}
